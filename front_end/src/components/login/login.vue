<style scoped>
    .login {
        height: 350px;
        width: 490px;
        top: 20%;
        position: relative;
        border-radius: 30px;
        text-align: center;
        background: rgb(27 29 70 / 85%);
        margin: auto;
    }

    #login_div {
        height: 100%;
        background-image: url("http://imagepphcloud.thepaper.cn/pph/image/60/539/62.jpg");

        background-size: 100% 100%;
    }
</style>
<template>
    <div id='login_div'>
        <div style="height: 6%;"></div>
        <div class="login">
            <br/>
            <br/>
            <h1 style="color: white;"><span>青少年培训机构学员管理系统登录</span></h1>
            <br/>
            <Form style="width: 350px;margin: auto;" :model="user">
                <div >
                    <FormItem>
                        <Input type="text" v-model="user.teacherId" :maxlength="15" placeholder="工号">
                            <Icon type="md-phone-portrait" slot="prepend"></Icon>
                        </Input>
                    </FormItem>
                    <FormItem>
                        <Input type="password" v-model="user.password" :maxlength="20" placeholder="密码">
                            <Icon type="ios-lock-outline" slot="prepend"></Icon>
                        </Input>
                    </FormItem>
                    <FormItem>
                        <a @click="handleSubmit()">
                            <Button type="success" onclick="return false" long style="margin-top: 15px;"> 登 录</Button>
                        </a>
                    </FormItem>
                </div>
            </Form>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                user: {
                    password: '',
                    teacherId: ''
                },
                login: false,
                code: 1024,
            }
        },
        methods: {
            handleSubmit() {
                if (this.user.teacherId.trim().length == 0) {
                    this.$Message.warning('请输入工号!');
                    return false;
                }
                if (this.user.password.trim().length == 0) {
                    this.$Message.warning('请输入密码!');
                    return false;
                }
                let th = this;
                axios.get('/student_manager/teacher/login', {
                    params: th.user
                }).then((res) => {
                    if (res.data.code === 200) {
                        localStorage.setItem("userInfo", JSON.stringify(res.data.data))
                        this.$Message.success('登录成功!');
                        window.location.href = "/#/manager/";
                    } else {
                        this.$Message.warning(res.data.message);
                    }
                });

            }
        },
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
