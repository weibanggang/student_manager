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
            <Form ref="course" inline>
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
            <Form ref="forms" :model="course" :rules="rule" :label-width="80">
                <Row>
                    <Col span="12">
                        <FormItem label="课程类型" prop="courseType">
                            <Select v-model="course.courseType" placeholder="请选择课程类型">
                                <Option v-for="item in courseTypeList" :value="item" :key="item">{{ item}}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="课程名称" prop="courseName">
                            <Input v-model="course.courseName" maxlength="100" placeholder="请输入课程名称"></Input>
                        </FormItem>
                    </Col>
                </Row>
                <FormItem label="备注" prop="remarks">
                    <Input v-model="course.remarks" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
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
                        title: '备注',
                        key: 'remarks',
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
                courseType: "",
                courseName: "",
                courseTypeList: ["艺术类", "文学类", "外语类"],
                course: {
                    uuid: "",
                    courseType: "",
                    courseName: "",
                    remarks: ''
                },
                rule: {
                    courseType: [
                        {required: true, message: '请选择课程类型', trigger: 'blur'},
                    ],
                    courseName: [
                        {required: true, message: '请输入课程名称', trigger: 'blur'},
                    ]
                }
            }
        },
        methods: {
            //单击添加
            add() {
                this.title = "新增";
                this.course = {
                    uuid: "",
                    courseType: "",
                    courseName: "",
                    remarks: ''
                };
                this.modal14 = true;
            },
            //单击编辑
            show(data) {
                this.title = '编辑'
                this.modal14 = true;
                this.course = JSON.parse(JSON.stringify(data));
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
                        axios.post('/student_manager/course/' + urls, th.course, {
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
                        axios.get('/student_manager/course/deleteByPrimaryKey', {
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
                    filename: '课程信息'
                });
            },
            changePage(page) {

                this.loading = true;
                if (!this.courseType) {
                    this.courseType = '';
                }
                if (!this.courseName) {
                    this.courseName = '';
                }
                const th = this;
                axios.get('/student_manager/course/selectPage', {
                    params: {
                        page: page,
                        courseType: th.courseType,
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
