<template>
    <div>
        <div style="position:relative;">
            <Input v-model="pName" style="width: 600px"></Input>
            <Button style="margin-left: 20px" type="primary" @click="changePage">搜索</Button>
        </div>
        <div v-if="dataSource.length > 0">
            <div v-for="item in dataSource">
                <pro :context="item.pName" :price="item.pPrice"
                     :url="'http://127.0.0.1:8081/florist/upload/getPhoto?url='+encodeURIComponent(item.pImg)"
                     :id="item.pId" @add="add"/>
            </div>
        </div>
        <div v-else style="font-size: 24px">
            <h1>无上架商品</h1>
        </div>
    </div>
</template>
<script>
    import pro from "../model/pro";

    export default {
        components: {pro},
        data() {
            return {
                dataSource: [],
                type: 0,
                pName: "",
                userInfo: "",
            }
        },
        created() {
            this.changePage();
        }, methods: {
            add(obj) {
                let userInfo = localStorage.getItem("userInfo");
                if (userInfo) {
                    this.userInfo = JSON.parse(userInfo);
                    if (this.userInfo.uId) {
                        let shoppingCart = {
                            sPid:obj.id,
                            sUid:this.userInfo.uId,
                            sStatus:'未下单',
                            sCount:'1',
                            s_money:obj.price
                        }
                        axios.post('/florist/shoppingCart/insert', shoppingCart, {
                            headers: {
                                "Content-Type": "application/json;charset=utf-8"
                            }
                        }).then( (res)=> {
                            if (res.data.code === 200) {
                                this.$Message.success(res.data.message);
                                setTimeout(()=>{
                                    this.$router.push({path:"/vip/gwcs",query:{uId:this.userInfo.uId}});
                                },500);
                            } else {
                                this.$Message.error(res.data.message);
                            }
                        })
                        console.log(shoppingCart)
                        return;
                    }
                }
                this.$router.push({path: "/", query: {type: 1}});
            },
            changePage() {
                axios.get('/florist/product/selectAll', {
                    params: {
                        type: this.type,
                        pName: this.pName
                    }
                }).then((res) => {
                    this.dataSource = res.data.data;
                })
            },
        },
        watch: {
            $route() {
                this.type = this.$route.query.type
                this.changePage();
            }
        }

    }
</script>
