<style>
    .ivu-table td, .ivu-table-border td {
        height: 41px;
    }
</style>
<template>
    <div>
        <div class="rigtop">
            <Form ref="productType" inline>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            类型名称
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="类型名称模糊查询" v-model="tName" icon="ios-search"  @on-click="changePage(1)"></Input>
                        </Col>
                    </Row>
                </FormItem>
                <!--	<FormItem>
                        <Row>
                            <Col span="8" style="text-align: center;">
                                状态
                            </Col>
                            <RadioGroup v-model="tStatus">
                                <Radio label="上架"></Radio>
                                <Radio label="下架"></Radio>
                            </RadioGroup>
                        </Row>
                    </FormItem>-->

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

        <Modal v-model="modal14" :loading="modal14loading" scrollable :title="title" @on-ok="addok">
            <Form ref="formValidate" :model="productType" :label-width="80">
                <FormItem label="类型名称" prop="tName">
                    <Input v-model="productType.tName" :maxlength=18 placeholder="请输入类型名称"></Input>
                </FormItem>
                <FormItem label="状态" prop="tStatus">
                    <RadioGroup v-model="productType.tStatus">
                        <Radio label="上架">上架</Radio>
                        <Radio label="下架">下架</Radio>
                    </RadioGroup>
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
                title: '添加类型',
                count: 10,
                columns7: [{
                    title: '编号',
                    key: 'tId',
                    align: 'center',
                    width: 100
                },
                    {
                        title: '类型名称',
                        key: 'tName',
                        align: 'center',
                        tooltip: true
                    },
                    {
                        title: '状态',
                        key: 'tStatus',
                        align: 'center'
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
                                            this.remove(params.row.tId)
                                        }
                                    }
                                }, '移除')
                            ]);
                        }
                    }
                ],
                data6: [],
                tName: "",
                productType: {
                    tId: "",
                    tName: '',
                    tStatus: "上架",
                },
            }
        },
        methods: {
            //单击添加
            add() {
                this.title = "新增类型";
                this.productType = {
                    tId: "",
                    tName: '',
                    tStatus: "上架",
                };
                this.modal14 = true;
            },
            //单击编辑
            show(data) {
                this.title = '编辑类型'
                this.modal14 = true;
                this.productType = JSON.parse(JSON.stringify(data));
            },
            //弹出添加保存
            addok() {
                if(this.productType.tName.trim().length ===0){
                    this.$Message.warning("类型名称不能为空");
                    return;
                }
                let th = this;
                var urls = "insert";
                if (this.title == "编辑类型") {
                    urls = "updateByPrimaryKey";
                }
                axios.post('/florist/productType/' + urls, th.productType, {
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


            },
            modal14show() {
                this.modal14 = false;
                setTimeout(() => {
                    this.modal14 = true;
                }, 0);
            },
            //删除操作
            remove(id) {
                this.$Modal.confirm({
                    title: '删除提示',
                    content: '<p>移除后不可恢复，确定继续？</p>',
                    onOk: () => {
                        const th = this;
                        axios.get('/florist/productType/deleteByPrimaryKey', {
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
                    filename: '类型信息'
                });
            },
            changePage(page) {
                const th = this;
                th.loading = true;
                axios.get('/florist/productType/selectPage', {
                    params: {
                        page: page,
                        tName: th.tName
                    }
                }).then(function (res) {
                    console.log(res)
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
