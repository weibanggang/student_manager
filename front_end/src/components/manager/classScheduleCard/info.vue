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
</style>
<template>
    <div>
        <div class="rigtop">
            <Form ref="classScheduleCard" inline>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            课程名称
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="课程名称模糊查询" v-model="className" icon="ios-search"
                                   @on-click="changePage(1)"></Input>
                        </Col>
                    </Row>
                </FormItem>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            星期
                        </Col>
                        <Col span="16">
                            <Select v-model="week" placeholder="星期" filterable clearable
                                    @on-change="changePage(1)">
                                <Option v-for="item in weeks" :value="item" :key="item">{{
                                    item}}
                                </Option>
                            </Select>
                        </Col>
                    </Row>
                </FormItem>

                <FormItem style="position: absolute;right: 30px">
                    <FormItem>
                        <Button @click="add()">
                            <Icon type="ios-add-circle-outline"/>
                            添加记录
                        </Button>
                    </FormItem>
                    <Button @click="exportData()">
                        <Icon type="ios-download-outline"/>
                        数据导出
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

        <Modal class-name="vertical-center-modal" v-model="modal14" :loading="modal14loading" width="800px" scrollable
               :title="title" @on-ok="addok">
            <Form ref="forms" :model="classScheduleCard" :rules="rule" :label-width="80">
                <Row>
                    <Col span="12">
                        <FormItem label="课程名称" prop="courseName">
                            <Select v-model="classScheduleCard.courseId"clearable placeholder="请选择课程名称"  filterable
                                    @on-change="courseNameChange($event)" >
                                <Option v-for="item in courseNameList" :value="item.uuid" :key="item.uuid">{{
                                    item.courseName}}
                                </Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="任课老师" prop="teacherName">
                            <Select v-model="classScheduleCard.teacherId" placeholder="请选择任课老师" filterable
                                    @on-change="teacheChange($event)">
                                <Option v-for="item in teacherList" :value="item.teacherId" :key="item.teacherId">{{
                                    item.name}}
                                </Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="开始时间" prop="startTime">
                            <DatePicker type="datetime" format="yyyy-MM-dd HH:mm:ss"
                                        :value="classScheduleCard.startTime"
                                        @on-change="setWeek($event)" placeholder="请选择开始时间"
                                        style="width: 100%"></DatePicker>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="结束时间" prop="endTime">
                            <DatePicker type="datetime" format="yyyy-MM-dd HH:mm:ss" :value="classScheduleCard.endTime"
                                        @on-change="classScheduleCard.endTime=$event" placeholder="请选择结束时间"
                                        style="width: 100%"></DatePicker>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="星期" prop="week">
                            <Input v-model="classScheduleCard.week" maxlength="5" disabled></Input>
                        </FormItem>
                    </Col>

                    <Col span="12">
                        <FormItem label="状态" prop="status">
                            <RadioGroup v-model="classScheduleCard.status">
                                <Radio label="未上课">未上课</Radio>
                                <Radio label="上课中">上课中</Radio>
                                <Radio label="已上课">已上课</Radio>
                            </RadioGroup>
                        </FormItem>
                    </Col>
                </Row>
                <FormItem label="备注" prop="remarks">
                    <Input v-model="classScheduleCard.remarks" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
                           maxlength="120"></Input>
                </FormItem>
            </Form>
        </Modal>

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
                    },
                    {
                        title: '状态',
                        key: 'status',
                        align: 'center',
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {

                                            this.show(params.row)
                                        }
                                    }
                                }, '编辑'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.remove(params.row.uuid)
                                        }
                                    }
                                }, '移除')
                            ]);
                        }
                    }
                ],
                data6: [],
                className: "",
                teacherList: [],
                courseNameList: [],
                week: "",
                weeks: ["日", "一", "二", "三", "四", "五", "六"],
                courseId: '',
                classScheduleCard: {
                    uuid: "",
                    courseName: '',
                    classType: "",
                    className: "",
                    remarks: ''
                },
                rule: {
                    classType: [
                        {required: true, message: '请选择课程类型', trigger: 'blur'},
                    ],
                    courseName: [
                        {required: true, message: '请输入课程名称', trigger: 'blur'},
                    ],
                    startTime: [
                        {required: true, message: '请输入课程名称', trigger: 'blur'},
                    ],
                    endTime: [
                        {required: true, message: '请输入课程名称', trigger: 'blur'},
                    ],
                    teacherName: [
                        {required: true, message: '请选择任课老师', trigger: 'blur'},
                    ]
                }
            }
        },
        methods: {
            //单击添加
            add() {
                this.title = "新增";
                this.classScheduleCard = {
                    courseName: "",
                    courseType: "",
                    teacherId: "",
                    courseId: "",
                    teacherName: "",
                    startTime: "",
                    endTime: "",
                    week: "",
                    status: "未上课",
                    remarks: ''
                };
                this.modal14 = true;
            },
            //单击编辑
            show(data) {
                this.title = '编辑'
                this.modal14 = true;
                this.classScheduleCard = JSON.parse(JSON.stringify(data));
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
            modal14show() {
                this.modal14 = false;
                this.$nextTick(() => {
                    this.modal14 = true;
                });
            },
            //删除操作
            remove(id) {
                this.$Modal.confirm({
                    title: '删除提示',
                    content: '<p>移除后不可恢复，确定继续？</p>',
                    onOk: () => {
                        const th = this;
                        axios.get('/student_manager/classScheduleCard/deleteByPrimaryKey', {
                            params: {
                                id: id
                            }
                        }).then(function (res) {
                            if (res.data.code === 200) {
                                th.$Message.success(res.data.message);
                                th.changePage(1);
                            } else {
                                th.$Message.error(res.data.message);
                            }
                        })
                    }
                });
            },
            //导出数据
            exportData() {
                this.$refs.table.exportCsv({
                    filename: '课程表信息'
                });
            },
            changePage(page) {

                this.loading = true;
                if (!this.week) {
                    this.week = '';
                }
                if (!this.className) {
                    this.className = '';
                }
                const th = this;
                axios.get('/student_manager/classScheduleCard/selectPage', {
                    params: {
                        page: page,
                        week: th.week,
                        className: th.className
                    }
                }).then(function (res) {
                    th.data6 = res.data.data;
                    th.count = res.data.count;
                })
                th.loading = false;
            },
            courseNameChange(value) {
                this.courseNameList.forEach((res) => {
                    if (res.uuid === value) {
                        this.classScheduleCard.courseId = res.uuid;
                        this.classScheduleCard.courseName = res.courseName;
                        this.classScheduleCard.courseType = res.courseType;
                    }
                })

            },
            teacheChange(value) {
                this.teacherList.forEach((res) => {
                    if (res.teacherId === value) {
                        this.classScheduleCard.teacherId = res.teacherId;
                        this.classScheduleCard.teacherName = res.name;
                    }
                })

            },
            getWeekDay(date) {
                let weekArray = new Array("日", "一", "二", "三", "四", "五", "六");
                let week = weekArray[new Date(date).getDay()];//注意此处必须是先new一个Date
                return week;
            },
            timestampToTime(timestamp) {
                let date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                let Y = date.getFullYear() + '-';
                let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                let D = date.getDate() + ' ';
                let h = date.getHours() + ':';
                let m = date.getMinutes() + ':';
                let s = date.getSeconds();
                return Y + M + D + h + m + s;
            },
            setWeek(event) {
                this.classScheduleCard.startTime = event;
                this.classScheduleCard.week = this.getWeekDay(event);
                this.classScheduleCard.endTime =this.timestampToTime(new Date(event).getTime() + 2 * 60 * 60 * 1000);
            }
        },
        created() {
            this.changePage(1);
            axios.get('/student_manager/teacher/selectAll').then((res) => {
                this.teacherList = res.data.data;
            })
            axios.get('/student_manager/course/selectAll').then((res) => {
                this.courseNameList = res.data.data;
            })
        }
    }
</script>
