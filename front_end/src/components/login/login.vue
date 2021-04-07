<style scoped>
    .login {
        height: 350px;
        width: 490px;
        top: 20%;
        position: relative;
        border-radius: 30px;
        text-align: center;
        background: rgba(0, 0, 0, 0.71);
        margin: auto;
    }

    #login_div {
        height: 100%;
        /*background-image: url("bj.jpg");*/

        background-size: 100% 100%;
    }
</style>
<template>
    <div id='login_div'>
        <div style="height: 6%;"></div>
        <div class="login">
            <br/>
            <br/>
            <h1 style="color: white;"><span>网上花店售卖平台</span><span
                    v-if="user.type">{{user.type == '1' ? '会员登录':'管理员登录'}}</span></h1>
            <br/>
            <Form style="width: 350px;margin: auto;" :model="user">
                <div v-if="!user.type">
                    <FormItem>
                        <a @click="submitmanager()">
                            <Button type="success" onclick="return false" style="height: 60px" long> 管理员</Button>
                            <br/>
                        </a><br/>
                        <a @click="submitVip()">
                            <Button type="success" onclick="return false" style="height: 60px" long> 会员中心</Button>
                            <br/>
                        </a><br/>
                    </FormItem>
                </div>
                <div v-else>
                    <FormItem>
                        <Input type="text" v-model="user.phone" :maxlength="11" placeholder="用户名">
                            <Icon type="md-phone-portrait" slot="prepend"></Icon>
                        </Input>
                    </FormItem>
                    <FormItem>
                        <Input type="password" v-model="user.password" :maxlength="20" placeholder="密码">
                            <Icon type="ios-lock-outline" slot="prepend"></Icon>
                        </Input>
                    </FormItem>
                    <div v-if="user.type == '1'" style="text-align: right;"><span class="cz" @click="modal14Show()">没有账号？注册</span></div>
                    <FormItem>
                        <a @click="handleSubmit()">
                            <Button type="success" onclick="return false" long style="margin-top: 15px;"> 登 录</Button>
                        </a>
                    </FormItem>
                </div>
            </Form>
        </div>
        <div style="height:6%;"></div>
        <Modal v-model="modal14" :loading="modal14loading" scrollable title="注册会员" @on-ok="addok">
            <Form ref="formValidate" :model="users" :label-width="80">
                <FormItem label="登录名" prop="uName">
                    <Input v-model="users.uNames" :maxlength=12 placeholder="请输入登录名"></Input>
                </FormItem>
                <FormItem label="密码" prop="uPwd">
                    <Input type="password" v-model="users.uPwd" placeholder="请输入密码"></Input>
                </FormItem>
                <FormItem label="姓名" prop="uName">
                    <Input v-model="users.uName" :maxlength=11 placeholder="请输入姓名"></Input>
                </FormItem>
                <FormItem label="手机号码" prop="uTel">
                    <Input v-model="users.uTel" maxlength="11" placeholder="请输入手机号码"></Input>
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                user: {
                    password: '',
                    phone: '',
                    type: '',
                },
                login: false,
                code: 1024,
                modal14: false,
                modal14loading: false,
                users: {
                    uId: "",
                    uName: '',
                    uNames: '',
                    uPwd: '',
                    uTel: '',
                    uType: '1',
                    uStatus: "正常",
                },
            }
        },
        methods: {
            submitmanager() {
                this.user = {
                    password: '',
                    phone: '',
                    type: 0,
                }
                window.location.href = "#/?type=0";
            },
            submitVip() {
                window.location.href = "#/?type=1";
                this.user = {
                    password: '',
                    phone: '',
                    type: 1,
                }
            },
            addok() {

                if(this.users.uNames.length == 0){
                    setTimeout(()=>{
                        this.modal14 = true;
                    },300)
                    this.$Message.warning("请输入登录名");
                    return ;
                }
                if(this.users.uPwd.length == 0){
                    setTimeout(()=>{
                        this.modal14 = true;
                    },300)
                    this.$Message.warning("请输入密码");
                    return ;
                }
                if(this.users.uName.length == 0){
                    setTimeout(()=>{
                        this.modal14 = true;
                    },300)
                    this.$Message.warning("请输入姓名");
                    return ;
                }
                if(this.users.uTel.length != 11){
                    setTimeout(()=>{
                        this.modal14 = true;
                    },300)
                    this.$Message.warning("请输入11位电话号码");
                    return ;
                }
                let th = this;
                th.modal14loading = true;
                axios.post('/florist/users/insert', th.users, {
                    headers: {
                        "Content-Type": "application/json;charset=utf-8"
                    }
                }).then(function (res) {
                    th.modal14loading = false;
                    console.log(res)
                    if (res.data.code === 200) {
                        th.$Message.success(res.data.message);
                        th.users = {
                            uId: "",
                            uName: '',
                            uNames: '',
                            uPwd: '',
                            uTel: '',
                            uType: '1',
                            uStatus: "正常",
                        }
                        th.modal14 = false;
                    } else {
                        th.modal14show();
                        th.$Message.error(res.data.message);
                    }
                })


            },
            modal14Show() {
                this.modal14 = !this.modal14;
            },
            handleSubmit() {
                if (this.user.password.trim().length == 0) {
                    this.$Message.warning('请输入密码!');
                    return false;
                }
                let th = this;
                axios.get('/florist/users/selectByLogin', {
                    params: th.user
                }).then((res) => {
                    if (res.data.code === 200) {
                        localStorage.setItem("userInfo", res.data.data)
                        this.$Message.success('登录成功!');
                        if (this.user.type == 1) {
                            window.location.href = "/#/vip/";
                        } else {
                            window.location.href = "/#/manager/";
                        }
                    } else {
                        this.$Message.warning(res.data.message);
                    }
                });

            }
        },
        created() {
            if (this.$route.query && this.$route.query.type) {
                this.user.type = this.$route.query.type;
            } else {
                this.user.type = "";
            }
        },
        watch: {
            $route(to) {
                if (to.query && to.query.type) {
                    this.user.type = to.query.type;
                } else {
                    this.user.type = "";
                }
            }
        }
    }
</script>
<style scoped>
    .cz {
        text-align: right;
        right: 20px;
        cursor: pointer;
    }

    .cz:hover {
        color: blue;
    }
</style>
