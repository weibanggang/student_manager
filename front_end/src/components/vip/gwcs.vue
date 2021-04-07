<template>
    <div>
        <div style="margin: auto;text-align: center"><h1>我的购物车</h1></div>

        <gwcPro @remove="remove" v-for="item in dataSource" :context="item.pName" :count="item.sCount" :price="item.pPrice" :url="'http://127.0.0.1:8081/florist/upload/getPhoto?url='+encodeURIComponent(item.pImg)" :id="item.sId"></gwcPro>
    </div>
</template>
<script>
    import gwcPro from "../model/gwcPro";
    export default {
        name: "gwcs",
        components:{gwcPro},
        data: function () {
            return {
                url:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fs7.sinaimg.cn%2Fbmiddle%2F60ee77c5t6ea3a2c6dd46%26690&refer=http%3A%2F%2Fs7.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1616513071&t=bace3daeb928ef74425c1813efe08f9f",
                dataSource:[]
            }
        },
        methods: {
            getData(){
                let userInfo = localStorage.getItem("userInfo");
                if (userInfo) {
                    this.userInfo = JSON.parse(userInfo);
                    console.log(this.userInfo )
                    axios.get('/florist/shoppingCart/selectByUid?id='+this.userInfo.uId).then( (res)=> {
                        if (res.data.code === 200) {
                            this.dataSource = res.data.data;
                        } else {
                            this.$Message.warning(res.data.message);
                        }
                    })
                }
            },
            remove(id){
                axios.get('/florist/shoppingCart/deleteByPrimaryKey?id='+id).then( (res)=> {
                    if (res.data.code === 200) {
                        this.$Message.success("删除成功！")
                        this.getData();
                    } else {
                        this.$Message.warning(res.data.message);
                    }
                })
            }
        },
        created() {
            this.getData();
        }
    }
</script>
