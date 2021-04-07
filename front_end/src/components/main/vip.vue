<style>
    .rigtop {
        width: 100%;
        left: 20;
        height: 45px;
        border: 1px solid #dcdee2;
        padding: 5px;
        line-height: 45px;
        margin: 5px 0;
    }

    .rigtop form {
        height: 35px;
    }

    .rigtop .ivu-select-selection, .ivu-select-input, .rigtop .ivu-input {
        height: 28px;
    }

    .rigtop .ivu-btn {
        padding: 4px 5px 4px;
    }
</style>
<style scoped>
    .ivu-layout-header {
        z-index: 999;
        padding: 0;
    }

    .ivu-menu-horizontal, .ivu-layout-header {
        height: 50px;
        line-height: 50px;
    }

    .ivu-menu-vertical {
        height: 100%;
    }

    .layout {
        background: #f5f7f9;
        position: relative;
        overflow: hidden;
    }

    .layout-logo {
        width: 100px;
        float: left;
        position: relative;
        left: 80px;
        width: 250px;
    }

    .layout-nav {
        right: 50px;
        position: absolute;
    }

    .layout-logos {
        left: 0;
        top: 30px;
        width: 250px;;
        padding-left: 26px;
    }
</style>
<template>
    <div class="layout">
        <Layout>
            <!--头部-->
            <Header style="height:80px;">
                <Menu mode="horizontal" :theme="theme" active-name="6"
                      :style="{position: 'fixed', width: '100%',}" style="line-height:80px;height:80px;">
                    <div class="layout-logo" style="cursor: pointer" @click="doVip"><h3>网上花店售卖网站</h3></div>

                    <div class="layout-nav">
                        <!--<MenuItem name="5">
                            我的订单
                        </MenuItem>-->
                        <MenuItem name="4" @click.native="gwcs" v-if="isLonginOk">
                            我的购物车
                        </MenuItem>
                        <MenuItem name="3" @click.native="logout">
                            {{isLonginOk ? '退出登陆' : '登录/注册'}}
                        </MenuItem>
                    </div>
                </Menu>

            </Header>
            <Layout>
                <!--左边-->
                <Sider hide-trigger style="background-color: white;" v-if="!gwc">
                    <Menu :accordion="true" active-name="1-2" :theme="theme"
                          width="auto"
                          :open-names="['1']">
                        <div v-for="(item,i) in type">
                            <router-Link :to="'/vip?type='+item.tId">
                                <MenuItem :name="item.tName">{{item.tName}}</MenuItem>
                            </router-Link>
                        </div>

                    </Menu>
                </Sider>
                <!--右边-->
                <Layout>
                    <!--内容-->
                    <Content :style="{padding: '0 25px', minHeight: '280px', background: '#fff'}">
                        <router-view>
                        </router-view>
                    </Content>
                </Layout>
            </Layout>
        </Layout>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                mName: "小邦哥1",
                theme: "light",
                pName: "",
                user: {
                    password: "",
                    passwords: "",
                    passwordss: ""
                },
                userInfo:'',
                isLonginOk: false,
                type: [],
                gwc: false
            }
        },
        methods: {
            logout() {
                if(this.isLonginOk){
                    localStorage.removeItem("userInfo");
                    this.$Message.success("退出成功！")
                    this.$router.push({path:"/vip"});
                    this.isLonginOk = false;
                }else{
                    this.$router.push({path:"/",query:{type:1}});
                }

            },
            doVip(){
                this.$router.push({path:"/vip"});
            },
            changePage() {
                axios.get('/florist/productType/selectAll').then((res) => {
                    this.type = res.data.data;
                })
            },
            gwcs() {
                if (this.isLonginOk) {
                    this.$router.push({path:"/vip/gwcs",query:{uId:this.userInfo.uId}});
                    this.gwc = true;
                }
            }
        },
        created() {
            let userInfo = localStorage.getItem("userInfo");
            if (userInfo) {
                this.userInfo = JSON.parse(userInfo);
                this.isLonginOk = true
            }
            if(this.$route.path.indexOf("gwcs") > 0){
                this.gwc = true;
            }else{
                this.gwc = false;
            }
            var data = new Date();
            this.years = data.getFullYear() + "年" + (data.getMonth() + 1) + "月" + data.getDate() + "日";
            this.changePage();
        },
        watch:{
            $route(to){
                if(to.path.indexOf("gwcs") > 0){
                    this.gwc = true;
                }else{
                    this.gwc = false;
                }
            }
        }
    }
</script>
