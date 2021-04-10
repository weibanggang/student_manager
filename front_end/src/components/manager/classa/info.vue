<style>
    .ivu-table td, .ivu-table-border td {
        height: 41px;
    }
</style>
<template>
    <div>
        <div class="rigtop">
            <Form ref="classa" inline>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            班级名称
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="班级名称模糊查询" v-model="className" icon="ios-search"
                                   @on-click="changePage(1)"></Input>
                        </Col>
                    </Row>
                </FormItem>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            班主任姓名
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="班主任姓名模糊查询" v-model="teacherName" icon="ios-search"
                                   @on-click="changePage(1)"></Input>
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


        <Table border :columns="columns7" :data="data6" height="450" :loading="loading" stripe size='default'
               ref="table"></Table>
        <div style="margin: 10px;overflow: hidden">
            <div style="float: right;">
                <Page :total="count" :current="1" @on-change="changePage($event)"></Page>
            </div>
        </div>

        <Modal v-model="modal14" :loading="modal14loading" scrollable :title="title" width="800px"  @on-ok="addok">
            <Form ref="forms" :model="classa" :rules="rule" :label-width="80">
                <Row>
                    <Col span="12">

                        <FormItem label="课程名称" prop="courseName">
                            <Select v-model="classa.courseId" placeholder="请选择课程名称" filterable @on-change="courseNameChange($event)">
                                <Option v-for="item in courseNameList" :value="item.uuid" :key="item.uuid">{{ item.courseName}}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="班级类别" prop="classType">
                            <Input v-model="classa.classType" disabled placeholder="选择课程名称后自动输入"></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="班级名称" prop="className">
                            <Input v-model="classa.className" :maxlength=18 placeholder="请输入班级名称"></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="班主任" prop="teacherName">
                            <Select v-model="classa.teacherId" placeholder="请选择班主任" filterable @on-change="teacheChange($event)">
                                <Option v-for="item in teacherList" :value="item.teacherId" :key="item.teacherId">{{ item.name}}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="开班日期" prop="startClassTime">
                            <DatePicker type="date" format="yyyy-MM-dd" @on-change="classa.startClassTime=$event"
                                        :value="classa.startClassTime" placeholder="请选择入职时间"
                                        style="width: 100%"></DatePicker>
                        </FormItem>
                    </Col>

                    <Col span="12">
                        <FormItem label="结班日期" prop="endClassTime">
                            <DatePicker type="date" format="yyyy-MM-dd" :value="classa.endClassTime"
                                        @on-change="classa.endClassTime=$event" placeholder="请选择出生日期"
                                        style="width: 100%"></DatePicker>
                        </FormItem>
                    </Col>

                    <Col span="6">
                        <FormItem label="预招人数" prop="expectNumberPeople">
                            <Input-number :max="1000" :min="1" v-model="classa.expectNumberPeople"></Input-number>
                        </FormItem>
                    </Col>
                    <Col span="6">
                        <FormItem label="实际人数" prop="actualNumberPeople">
                            <Input-number :max="1000" :min="1" v-model="classa.actualNumberPeople"></Input-number>
                        </FormItem>
                    </Col>
                    <Col span="6">
                        <FormItem label="计划课时" prop="actualNumberPeople">
                            <Input-number :max="1000" :min="1" v-model="classa.planNumber"></Input-number>
                        </FormItem>
                    </Col>
                    <Col span="6">
                        <FormItem label="已上课时" prop="actualNumberPeople">
                            <Input-number :max="1000" :min="1" v-model="classa.alreadyNumber"></Input-number>
                        </FormItem>
                    </Col>

                    <Col span="12">
                        <FormItem label="联系电话" prop="phone">
                            <Input v-model="classa.phone" maxlength="11" placeholder="请输入联系电话"></Input>
                        </FormItem>
                    </Col>
                </Row>

                <FormItem label="备注" prop="remarks">
                    <Input v-model="classa.remarks" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
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
                modal14loading: true,
                title: '添加管理员',
                count: 10,
                courseNameList: [],
                teacherList: [],
                columns7: [
                    {
                        title: '班级类别',
                        key: 'classType',
                        align: 'center',
                        width: 100
                    }, {
                        title: '班级名称',
                        key: 'className',
                        align: 'center',
                    },
                    {
                        title: '班主任',
                        key: 'teacherName',
                        align: 'center',
                    },
                    {
                        title: '手机号码',
                        key: 'phone',
                        align: 'center'
                    },
                    {
                        title: '开班日期',
                        key: 'startClassTime',
                        align: 'center',
                    },
                    {
                        title: '结班日期',
                        key: 'endClassTime',
                        align: 'center',
                    },
                    {
                        title: '招人数',
                        key: 'expectNumberPeople',
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
                teacherName: "",
                courses:"",
                classa: {
                    uuid: "",
                    classType: '',
                    className: '',
                    teacherId: '',
                    teacherName: '',
                    phone: '',
                    startClassTime: "",
                    endClassTime: '',
                    expectNumberPeople: 1,
                    actualNumberPeople: 1,
                    planNumber: 1,
                    alreadyNumber: 1,
                    courseId: '',
                    courseName: "",
                    remarks: ""
                },
                rule: {
                    courseName: [
                        {required: true, message: '请选择课程名称', trigger: 'change'},
                    ],
                    className: [
                        {required: true, message: '请输入班级名称', trigger: 'change'},
                    ],
                    phone: [
                        {required: true, message: '请输入手机号码', trigger: 'change'},
                    ],
                    teacherName: [
                        {required: true, message: '请选择班主任', trigger: 'change'},
                    ],
                    startClassTime: [
                        {required: true, message: '请选择开班日期', trigger: 'change'},
                    ],
                    endClassTime: [
                        {required: true, message: '请选择结班日期', trigger: 'change'},
                    ]
                }
            }
        },
        methods: {
            //单击添加
            add() {
                this.title = "新增";
                this.classa = {
                    classType: '',
                    className: '',
                    teacherId: '',
                    teacherName: '',
                    phone: '',
                    startClassTime: "",
                    endClassTime: '',
                    expectNumberPeople: 1,
                    actualNumberPeople: 1,
                    planNumber: 1,
                    alreadyNumber: 1,
                    courseId: '',
                    courseName: "",
                    remarks: ""
                };
                this.modal14 = true;
            },
            courseNameChange(value){
                this.courseNameList.forEach((res)=>{
                    if(res.uuid === value){
                        this.classa.courseId = res.uuid;
                        this.classa.courseName = res.courseName;
                        this.classa.className = new Date().getFullYear()+"-第期-"  + res.courseName ;
                        this.classa.classType = res.courseType;
                    }
                })

            },
            teacheChange(value){
                this.teacherList.forEach((res)=>{
                    if(res.teacherId === value){
                        this.classa.teacherId = res.teacherId;
                        this.classa.teacherName = res.name;
                        if(!this.classa.phone || this.classa.phone.length === 0){
                            this.classa.phone = res.phone;
                        }
                    }
                })

            },
            //单击编辑
            show(data) {
                this.title = '编辑'
                this.modal14 = true;
                this.classa = JSON.parse(JSON.stringify(data));
            },
            //弹出添加保存
            addok() {
                this.$refs["forms"].validate((valid) => {
                    if (valid) {
                        let th = this;
                        var urls = "insert";
                        if (this.title == "编辑") {
                            urls = "updateByPrimaryKey";
                        }
                        axios.post('/student_manager/classa/' + urls, th.classa, {
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
                });


            },
            modal14show() {
                this.modal14 = false;
                this.$nextTick(()=>{
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
                        axios.get('/student_manager/classa/deleteByPrimaryKey', {
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
                    filename: '班级信息'
                });
            },
            changePage(page) {

                this.loading = true;
                if (!this.className) {
                    this.className = '';
                }
                if (!this.teacherName) {
                    this.teacherName = '';
                }
                const th = this;
                axios.get('/student_manager/classa/selectPage', {
                    params: {
                        page: page,
                        className: th.className,
                        teacherName: th.teacherName
                    }
                }).then(function (res) {
                    th.data6 = res.data.data;
                    th.count = res.data.count;
                })
                th.loading = false;
            },
            init(){
                axios.get('/student_manager/teacher/selectAll').then( (res)=> {
                    this.teacherList = res.data.data;
                })
                axios.get('/student_manager/course/selectAll').then( (res)=> {
                    this.courseNameList = res.data.data;
                })
            },
        },
        created() {
            this.changePage(1);
            this.init();
        }
    }
</script>
