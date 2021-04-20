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
    }

    .ivu-menu-horizontal, .layout-logo, .ivu-layout-header {
        height: 50px;
        line-height: 50px;
        top: 0px;
    }

    .ivu-menu-vertical {
        height: 607px;
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
        top: 15px;
        left: 20px;
    }

    .layout-nav {
        right: 50px;
        position: absolute;
    }
</style>
<template>
    <div class="layout">
        <Layout>
            <!--头部-->
            <Header>
                <Menu mode="horizontal" :theme="theme" active-name="6" :style="{position: 'fixed', width: '100%'}">
                    <div class="layout-logo" style="left:-50px;top:0;width:50%;text-align: center; ">
                        <Menu mode="horizontal" @on-select="topfu" :accordion="true" :theme="theme" active-name="1">
                            <div class="layout-logo"
                                 style="left:0;top:0;width: 280px;;text-align: center;padding-left: 6px;">
                                <router-Link to="/manager">
                                <MenuItem name="首页"><h3 style="font-size: 18px;">青少年培训机构学员管理系统</h3></MenuItem>
                                </router-Link>
                            </div>
                        </Menu>
                    </div>
                    <div class="layout-nav">
                        <MenuItem name="5">

                            <Dropdown>
                                欢迎您！{{mName}}
                                <!--<Icon type="ios-arrow-down"></Icon>-->
                                <!--<DropdownMenu slot="list">
                                    <DropdownItem  @click.native="updatepassword">修改密码</DropdownItem>
                                </DropdownMenu>-->
                            </Dropdown>
                            <Modal v-model="modal13" draggable scrollable title="修改密码" @on-ok="ok">
                                <Form ref="formValidate" :model="user" :label-width="80">
                                    <FormItem label="旧密码" prop="password">
                                        <Input type="password" v-model="user.password" placeholder="请输入旧密码"></Input>
                                    </FormItem>
                                    <FormItem label="新密码" prop="passwords">
                                        <Input type="password" v-model="user.passwords" placeholder="请输入新密码"></Input>
                                    </FormItem>
                                    <FormItem label="确认密码" prop="passwordss">
                                        <Input type="password" v-model="user.passwordss" placeholder="请输入确认密码"></Input>
                                    </FormItem>
                                </Form>
                            </Modal>
                        </MenuItem>
                        <Submenu name="6">
                            <template slot="title">
                                <Icon type="ios-shirt-outline"/>
                                更换主题
                            </template>
                            <div style="background-color: #1b315e">
                                <RadioGroup v-model="theme">
                                    <Radio label="dark">
                                        <Icon type="social-apple"></Icon>
                                        <span>黑色主题</span>
                                    </Radio>
                                    <Radio label="light">
                                        <Icon type="social-android"></Icon>
                                        <span>白色主题</span>
                                    </Radio>
                                    <Radio label="primary">
                                        <Icon type="social-windows"></Icon>
                                        <span>飘色主题</span>
                                    </Radio>
                                </RadioGroup>
                            </div>

                        </Submenu>
                        <MenuItem name="3" @click.native="logout">
                            <Icon type="ios-exit-outline"/>
                            <span>退出</span>
                        </MenuItem>
                    </div>

                </Menu>
            </Header>
            <Layout>
                <!--左边-->
                <Sider hide-trigger :style="{background: '#fff'}">
                    <Menu @on-open-change="fu" @on-select="ze" :accordion="true" active-name="1-2" :theme="theme"
                          width="auto">
                        <Submenu name="信息管理">
                            <template slot="title">
                                <Icon type="md-contacts" />
                                信息管理
                            </template>
                            <router-Link to="/student/info">
                                <MenuItem name="学生信息管理">
                                    学生信息管理
                                </MenuItem>
                            </router-Link>
                            <router-Link to="/teacher/info">
                                <MenuItem name="教师信息管理">
                                    教师信息管理
                                </MenuItem>
                            </router-Link>
                            <router-Link to="/classa/info">
                                <MenuItem name="班级信息管理" v-if="obj.position != '任课老师'">
                                    班级信息管理
                                </MenuItem>
                            </router-Link>
                        </Submenu>
                        <Submenu name="课程管理">
                            <template slot="title">
                                <Icon type="ios-book"/>
                                课程管理
                            </template>
                            <router-Link to="/course/info">
                                <MenuItem name="课程种类管理" v-if="obj.position == '主管'">
                                    课程种类管理
                                </MenuItem>
                            </router-Link>
                            <router-Link to="/classScheduleCard/info" >
                                <MenuItem name="课程表管理">
                                    课程表管理
                                </MenuItem>
                            </router-Link>
                        </Submenu>
                        <Submenu name="考勤管理">
                            <template slot="title">
                                <Icon type="ios-ribbon"/>
                                考勤管理
                            </template>
                            <router-Link to="/teacherCheck/info"  >
                                <MenuItem name="教师考勤记录">教师考勤记录</MenuItem>
                            </router-Link>
                            <router-Link to="/studentCheck/info">
                                <MenuItem name="学生考勤记录">学生考勤记录</MenuItem>
                            </router-Link>
                            <router-Link to="/check/info" >
                                <MenuItem name="上课签到">上课签到</MenuItem>
                            </router-Link>
                        </Submenu>
                        <Submenu name="其他管理">
                            <template slot="title">
                                <Icon type="ios-settings" />
                                其他管理
                            </template>
                            <router-Link to="/satisfaction/info">
                                <MenuItem name="满意度调查">满意度调查</MenuItem>
                            </router-Link>
                        </Submenu>
                    </Menu>
                </Sider>
                <!--右边-->
                <Layout>
                    <!--导航栏-->
                    <Breadcrumb :style="{margin: '5px'}">
						<span>
							<Icon type="ios-pin-outline"/>
							当前位置：</span>
                        <span>{{ parentTag }}</span>
                        <span>{{ childTag }}</span>
                        <span style="float: right; margin-right: 20px;">{{years}}</span>
                    </Breadcrumb>
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
                years: "",
                mName: "",
                theme: "primary",
                parentTag: "控制台",
                modal13: false,
                childTag: "",
                obj:{
                    position:"班主任"
                },
                user: {
                    password: "",
                    passwords: "",
                    passwordss: ""
                }
            }
        },
        methods: {
            logout() {
                localStorage.removeItem("userInfo");
                window.location.href = "/#/"
            },
            ok() {
                var th = this;
                if (th.user.passwords != th.user.passwordss) {
                    th.$Message.warning("两次密码不一致");
                    th.modal13show();
                    return;
                }
                th.user.mUser = localStorage.getItem("mUser");
                axios.get('/FreightTransport/_manager/updatePassword', {
                    params: {
                        id: th.user.mUser,
                        old: th.user.password,
                        password: th.user.passwords
                    }
                }).then(function (res) {
                    if (res.data.code == 200) {
                        th.$Message.success(res.data.message);
                        localStorage.setItem("accessToken", null);
                        axios.get('/FreightTransport/login/logout');
                        setTimeout(function () {
                            window.location.href = "/manager";
                        }, 500);
                    } else {
                        th.$Message.error(res.data.message);
                    }
                })
            },
            modal13show() {
                this.modal13 = false;
                setTimeout(() => {
                    this.modal13 = true;
                }, 1000);
            },
            updatepassword() {
                this.modal13 = true;
            },
            fu(e) {
                this.parentTag = e[0];
                this.childTag = "";
            },
            ze(e) {
                this.childTag = " > " + e;
            },
            topfu(e) {
                this.parentTag = e;
                this.childTag = "";
            },
        },
        created() {
            let userInfo = localStorage.getItem("userInfo");
            console.log(userInfo)
            if(userInfo){
                userInfo = JSON.parse(userInfo);
            }else{
                window.location.href = "/#/";
            }
            this.mName = userInfo.name;
            this.obj = userInfo;
            var data = new Date();
            this.years = data.getFullYear() + "年" + (data.getMonth() + 1) + "月" + data.getDate() + "日";
        }
    }
</script>
