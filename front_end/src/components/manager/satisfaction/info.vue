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
            <Form ref="satisfaction" inline>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            调查人
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="调查人模糊查询" v-model="investigator" icon="ios-search"
                                   @on-click="changePage(1)"></Input>
                        </Col>
                    </Row>
                </FormItem>
                <FormItem style="width: 400px">
                    <Row>
                        <Col span="8" style="text-align: center;">
                            调查满意度
                        </Col>
                        <Col span="16">
                            <RadioGroup v-model="surveySatisfaction" @on-change="changePage(1)">
                                <Radio label="">全部</Radio>
                                <Radio label="非常满意">非常满意</Radio>
                                <Radio label="一般">一般</Radio>
                                <Radio label="不满意">不满意</Radio>
                            </RadioGroup>
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
            <Form ref="forms" :model="satisfaction" :rules="rule" :label-width="130">
                <Row>
                    <Col span="12">
                        <FormItem label="调查人" prop="investigator">
                            <Input v-model="satisfaction.investigator" maxlength="100" placeholder="请输入调查人"></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="被调查人" prop="respondents">
                            <Input v-model="satisfaction.respondents" maxlength="100" placeholder="请输入被调查人"></Input>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="调查时间" prop="time">
                            <DatePicker type="date" format="yyyy-MM-dd" @on-change="satisfaction.time=$event"
                                        :value="satisfaction.time" placeholder="请选择调查时间"
                                        style="width: 100%"></DatePicker>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem label="调查满意度" prop="surveySatisfaction">
                            <RadioGroup v-model="satisfaction.surveySatisfaction">
                                <Radio label="非常满意">非常满意</Radio>
                                <Radio label="一般">一般</Radio>
                                <Radio label="不满意">不满意</Radio>
                            </RadioGroup>
                        </FormItem>
                    </Col>
                </Row>
                <FormItem label="调查内容" prop="content">
                    <Input v-model="satisfaction.content" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
                           maxlength="120" placeholder="请输入调查内容"></Input>
                </FormItem>
                <FormItem label="学生情况" prop="remarks">
                    <Input v-model="satisfaction.remarks" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
                           maxlength="120" placeholder="请输入学生情况"></Input>
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
                        title: '调查时间',
                        key: 'time',
                        align: 'center',
                    },
                    {
                        title: '调查人',
                        key: 'investigator',
                        align: 'center',
                    },
                    {
                        title: '被调查人',
                        key: 'respondents',
                        align: 'center',
                    },
                    {
                        title: '满意度',
                        key: 'surveySatisfaction',
                        align: 'center',
                    },
                    {
                        title: '调查内容',
                        key: 'content',
                        align: 'center',
                    },
                    {
                        title: '学生情况',
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
                investigator: "",
                surveySatisfaction: "",
                satisfaction: {
                    uuid: "",
                    surveySatisfaction: "",
                    time: "",
                    content: "",
                    investigator: "",
                    respondents: "",
                    remarks: ''
                },
                rule: {
                    time: [
                        {required: true, message: '请输入调查时间', trigger: 'blur'},
                    ],
                    content: [
                        {required: true, message: '请输入调查内容', trigger: 'blur'},
                    ],
                    investigator: [
                        {required: true, message: '请输入调查人', trigger: 'blur'},
                    ],
                    respondents: [
                        {required: true, message: '请输入被调查人', trigger: 'blur'},
                    ],
                    remarks: [
                        {required: true, message: '请输入学生情况', trigger: 'blur'},
                    ]
                }
            }
        },
        methods: {
            //单击添加
            add() {
                this.title = "新增";
                this.satisfaction = {
                    surveySatisfaction: "非常满意",
                    time: "",
                    content: "",
                    investigator: "",
                    respondents: "",
                };
                this.modal14 = true;
            },
            //单击编辑
            show(data) {
                this.title = '编辑'
                this.modal14 = true;
                this.satisfaction = JSON.parse(JSON.stringify(data));
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
                        axios.post('/student_manager/satisfaction/' + urls, th.satisfaction, {
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
                        axios.get('/student_manager/satisfaction/deleteByPrimaryKey', {
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
                    filename: '调查满意度'
                });
            },
            changePage(page) {

                this.loading = true;
                if (!this.investigator) {
                    this.investigator = '';
                }
                if (!this.surveySatisfaction) {
                    this.surveySatisfaction = '';
                }
                const th = this;
                axios.get('/student_manager/satisfaction/selectPage', {
                    params: {
                        page: page,
                        investigator: th.investigator,
                        surveySatisfaction: th.surveySatisfaction
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
