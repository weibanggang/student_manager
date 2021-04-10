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
            <Form ref="teacher" inline>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            教师姓名
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="教师姓名模糊查询" v-model="teacherName" icon="ios-search"
                                   @on-click="changePage(1)"></Input>
                        </Col>
                    </Row>
                </FormItem>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            教师工号
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="教师工号模糊查询" v-model="teacherUUID" icon="ios-search"
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


        <Table border :columns="Columns7" :data="data6" height="450" :loading="loading" stripe size='default'
               ref="table"></Table>
        <div style="margin: 10px;overflow: hidden">
            <div style="float: right;">
                <Page :total="count" :current="1" @on-change="changePage($event)"></Page>
            </div>
        </div>

        <Modal class-name="vertical-center-modal" v-model="modal14" :loading="modal14loading" width="800px" scrollable
               :title="title" @on-ok="addok">
            <Form ref="forms" :model="teacher" :rules="rule" :label-width="80">
                <Row>
                    <Col span="12">
                        <FormItem label="教师工号" prop="teacherId">
                            <Input v-model="teacher.teacherId" disabled></Input>
                        </FormItem>
                    </Col>

                    <Col span="12">
                        <FormItem label="姓名" prop="name">
                            <Input v-model="teacher.name" :maxlength=18 placeholder="请输入名字"></Input>
                        </FormItem>
                    </Col>

                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="身份证" prop="tidCard">
                            <Input v-model="teacher.tidCard" maxlength="18" placeholder="请输入身份证"></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="性别" prop="sex">
                            <RadioGroup v-model="teacher.sex">
                                <Radio label="男">男</Radio>
                                <Radio label="女">女</Radio>
                            </RadioGroup>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem label="入职时间" prop="political">
                            <DatePicker type="date" format="yyyy-MM-dd" @on-change="teacher.political=$event"
                                        :value="teacher.political" placeholder="请选择入职时间"
                                        style="width: 100%"></DatePicker>
                        </FormItem>
                    </Col>

                    <Col span="12">
                        <FormItem label="出生日期" prop="birthday">
                            <DatePicker type="date" format="yyyy-MM-dd" :value="teacher.birthday"
                                        @on-change="teacher.birthday=$event" placeholder="请选择出生日期"
                                        style="width: 100%"></DatePicker>
                        </FormItem>
                    </Col>

                    <Col span="12">
                        <FormItem label="学历" prop="education">
                            <Select v-model="teacher.education" placeholder="请选择学历">
                                <Option v-for="item in educationList" :value="item" :key="item">{{ item}}</Option>
                            </Select>
                        </FormItem>
                    </Col>


                    <Col span="12">
                        <FormItem label="状态" prop="state">
                            <RadioGroup v-model="teacher.state">
                                <Radio label="在职">在职</Radio>
                                <Radio label="离职">离职</Radio>
                            </RadioGroup>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="手机号码" prop="phone">
                            <Input v-model="teacher.phone" maxlength="11" placeholder="请输入手机号码"></Input>
                        </FormItem>
                    </Col>

                    <Col span="12">
                        <FormItem label="职位" prop="position">
                            <RadioGroup v-model="teacher.position">
                                <Radio label="主管">主管</Radio>
                                <Radio label="班主任">班主任</Radio>
                                <Radio label="任课老师">任课老师</Radio>
                               <!-- <Radio label="招生老师">招生老师</Radio>-->
                            </RadioGroup>
                        </FormItem>
                    </Col>
                </Row>
                <Row>

                    <Col span="12">
                        <FormItem label="家庭住址" prop="home">
                            <Input v-model="teacher.home" maxlength="100" placeholder="请输入家庭住址"></Input>
                        </FormItem>
                    </Col>
                </Row>
                <FormItem label="备注" prop="remarks">
                    <Input v-model="teacher.remarks" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
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
                        title: '工号',
                        key: 'teacherId',
                        align: 'center',
                        width: 200
                    }, {
                        title: '姓名',
                        key: 'name',
                        align: 'center',
                    },
                    {
                        title: '性别',
                        key: 'sex',
                        align: 'center',
                    },
                    {
                        title: '手机号码',
                        key: 'phone',
                        align: 'center'
                    },
                    {
                        title: '状态',
                        key: 'state',
                        align: 'center',
                    },
                    {
                        title: '职位',
                        key: 'position',
                        align: 'center',
                    },
                    {
                        title: '入职时间',
                        key: 'political',
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
                                            this.remove(params.row.teacherId)
                                        }
                                    }
                                }, '移除')
                            ]);
                        }
                    }
                ],
                data6: [],
                teacherName: "",
                teacherUUID: "",
                educationList: ["专科", "本科", "硕士", "博士"],
                teacher: {
                    teacherId: new Date().getTime(),
                    name: '',
                    sex: '男',
                    birthday: '',
                    position: '主管',
                    phone: '',
                    password: "",
                    political: '',
                    tidCard: '',
                    education: "",
                    home: "",
                    state: '在职',
                    remarks: ''
                },
                rule: {
                    name: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                    ],
                    birthday: [
                        {required: true, message: '请选择出生时间', trigger: 'blur'},
                    ],
                    phone: [
                        {required: true, message: '请输入手机号码', trigger: 'blur'},
                    ],
                    political: [
                        {required: true, message: '请选择入职时间', trigger: 'blur'},
                    ],
                    tidCard: [
                        {required: true, message: '请输入身份证', trigger: 'blur'},
                    ],
                    education: [
                        {required: true, message: '请选择学历', trigger: 'blur'},
                    ],
                    home: [
                        {required: true, message: '请输入地址', trigger: 'blur'},
                    ]
                }
            }
        },
        methods: {
            //单击添加
            add() {
                this.title = "新增";
                this.teacher = {
                    teacherId: new Date().getTime(),
                    name: '',
                    sex: '男',
                    birthday: '',
                    position: '主管',
                    phone: '',
                    password: "",
                    political: '',
                    tidCard: '',
                    education: "",
                    home: "",
                    state: '在职',
                    remarks: ''
                };
                this.modal14 = true;
            },
            //单击编辑
            show(data) {
                this.title = '编辑'
                this.modal14 = true;
                this.teacher = JSON.parse(JSON.stringify(data));
                console.log(this.teacher)
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
                        axios.post('/student_manager/teacher/' + urls, th.teacher, {
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
                return false;
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
                        axios.get('/student_manager/teacher/deleteByPrimaryKey', {
                            params: {
                                id: id
                            }
                        }).then(function (res) {
                            console.log(res)
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
                    filename: '教师信息'
                });
            },
            changePage(page) {

                this.loading = true;
                if (!this.teacherName) {
                    this.teacherName = '';
                }
                if (!this.teacher_name) {
                    this.teacher_name = '';
                }
                const th = this;
                axios.get('/student_manager/teacher/selectPage', {
                    params: {
                        page: page,
                        teacherName: th.teacherName,
                        teacherUUID: th.teacherUUID
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
