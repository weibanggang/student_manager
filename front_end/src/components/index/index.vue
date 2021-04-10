<style>
    .ivu-table td, .ivu-table-border td {
        height: 41px;
    }
</style>
<style lang="less">
    .vertical-center-modal {
        display: flex;
        align-items: center;
        justify-content: center;

        .ivu-modal {
            top: 0;
        }
    }
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

</style>
<template>
    <div  style="padding: 0 50px">
        <h1 style="text-align: center;padding: 20px 0;">青少年培训机构学员课程表安排</h1>
        <div class="rigtop">
            <Form ref="classScheduleCard" inline>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            课程类型
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="课程类型模糊查询" v-model="courseType" icon="ios-search"
                                   @on-click="changePage(1)"></Input>
                        </Col>
                    </Row>
                </FormItem>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            课程名称
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="课程名称模糊查询" v-model="courseName" icon="ios-search"
                                   @on-click="changePage(1)"></Input>
                        </Col>
                    </Row>
                </FormItem>

                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            任课老师
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="任课老师模糊查询" v-model="teacherName" icon="ios-search"
                                   @on-click="changePage(1)"></Input>
                        </Col>
                    </Row>
                </FormItem>
                <FormItem>
                    <Button @click="reset()">
                        重置
                    </Button>
                </FormItem>
            </Form>
        </div>


        <Table border :columns="Columns7" :data="data6" height="450" :loading="loading" stripe size='default'
               ref="table"></Table>
        <div style="margin: 10px;overflow: hidden">
            <div style="float: right;">
                <Page :total="count" :current="1" @on-change="changePage($event)"></Page>
            </div>
        </div>

    </div>
</template>
<script>
    export default {
        data() {
            return {
                modal14: false,
                loading: true,
                modal14loading: false,
                title: '添加',
                count: 10,
                Columns7: [
                    {
                        title: '课程类型',
                        key: 'courseType',
                        align: 'center',
                        width:120
                    },
                    {
                        title: '课程名称',
                        key: 'courseName',
                        align: 'center',
                    },

                    {
                        title: '任课老师',
                        key: 'teacherName',
                        align: 'center',
                    },
                    {
                        title: '开始时间',
                        key: 'startTime',
                        align: 'center',
                    },
                    {
                        title: '结束时间',
                        key: 'endTime',
                        align: 'center',
                    },
                    {
                        title: '星期',
                        key: 'week',
                        align: 'center',
                        width:80
                    },
                    {
                        title: '状态',
                        key: 'status',
                        align: 'center',
                        width:100
                    },
                    {
                        title: '备注',
                        key: 'remarks',
                        align: 'center',
                    }
                ],
                data6: [],
                courseName: "",
                courseType:"",
                teacherName:"",
            }
        },
        methods: {
            //单击添加
            reset() {
                this.courseName="";
                this.courseType="";
                 this.teacherName="";
                this.changePage(1);
            },
            //弹出添加保存
            addok() {
                this.$refs["forms"].validate((valid) => {
                    if (valid) {
                        let th = this;
                        let urls = "insert";
                        if (this.title == "编辑") {
                            urls = "updateByPrimaryKey";
                        }
                        axios.post('/student_manager/classScheduleCard/' + urls, th.classScheduleCard, {
                            headers: {
                                "Content-Type": "application/json;charset=utf-8"
                            }
                        }).then(function (res) {
                            if (res.data.code === 200) {
                                th.$Message.success(res.data.message);
                                th.modal14 = false;
                                th.changePage(1);
                            } else {
                                th.modal14show();
                                th.$Message.error(res.data.message);
                            }
                        })
                    } else {
                        this.modal14show();
                        this.$Message.error('请填写必填项!');
                    }
                })
            },
            changePage(page) {

                this.loading = true;
                if (!this.week) {
                    this.week = '';
                }
                if (!this.courseName) {
                    this.courseName = '';
                }
                const th = this;
                axios.get('/student_manager/classScheduleCard/selectPage', {
                    params: {
                        page: page,
                        courseType: th.courseType,
                        teacherName: th.teacherName,
                        courseName: th.courseName
                    }
                }).then(function (res) {
                    th.data6 = res.data.data;
                    th.count = res.data.count;
                })
                th.loading = false;
            },
        },
        created() {
            this.changePage(1);
        }
    }
</script>
