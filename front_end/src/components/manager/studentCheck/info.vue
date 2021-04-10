<style>
    .ivu-table td, .ivu-table-border td {
        height: 41px;
    }
</style>
<template>
    <div>
        <div class="rigtop">
            <Form ref="studentCheck" inline>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            课程/时间
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="课程/时间模糊查询" v-model="courseNameSETime" icon="ios-search"
                                   @on-click="changePage(1)"></Input>
                        </Col>
                    </Row>
                </FormItem>
                <FormItem style="width: 400px">
                    <Row>
                        <Col span="8" style="text-align: center;">
                            考勤状态
                        </Col>
                        <Col span="16">
                            <RadioGroup v-model="status" @on-change="changePage(1)">
                                <Radio label="">全部</Radio>
                                <Radio label="未签到">未签到</Radio>
                                <Radio label="已签到">已签到</Radio>
                            </RadioGroup>
                        </Col>
                    </Row>
                </FormItem>

                <FormItem style="position: absolute;right: 30px">
                    <Button @click="exportData()">
                        <Icon type="ios-download-outline"/>
                        数据导出
                    </Button>
                </FormItem>
            </Form>
        </div>


        <Table border :columns="columns7" :data="data6" height="450" :loading="loading" stripe size='default'
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
                modal14loading: true,
                title: '添加管理员',
                count: 10,
                courseNameList: [],
                teacherList: [],
                columns7: [
                    {
                        title: '课程/时间',
                        key: 'courseNameSETime',
                        align: 'center',
                        width: 300
                    }, {
                        title: '学生姓名',
                        key: 'studentName',
                        align: 'center',
                    },
                    {
                        title: '学生ID',
                        key: 'studentId',
                        align: 'center',
                    },
                    {
                        title: '考勤状态',
                        key: 'status',
                        align: 'center',
                    },
                ],
                data6: [],
                courseNameSETime: "",
                status:'',
            }
        },
        methods: {
            //导出数据
            exportData() {
                this.$refs.table.exportCsv({
                    filename: '学生考勤信息'
                });
            },
            changePage(page) {
                this.loading = true;
                if (!this.courseNameSETime) {
                    this.courseNameSETime = '';
                }
                const th = this;
                axios.get('/student_manager/studentCheck/selectPage', {
                    params: {
                        page: page,
                        courseNameSETime: th.courseNameSETime,
                        status:th.status
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
