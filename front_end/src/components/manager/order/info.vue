<style>
    .ivu-table td, .ivu-table-border td {
        height: 41px;
    }
</style>
<template>
    <div>
        <div class="rigtop">
            <Form ref="product" inline>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            订单编号
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="订单编号模糊查询" v-model="pId" icon="ios-search" @on-click=" changePage(1)"></Input>
                        </Col>
                    </Row>
                </FormItem>
                <FormItem>
                    <Row>
                        <Col span="8" style="text-align: center;">
                            商品名称
                        </Col>
                        <Col span="16">
                            <Input height="20" placeholder="商品名称模糊查询" v-model="pName" icon="ios-search" @on-click=" changePage(1)"></Input>
                        </Col>
                    </Row>
                </FormItem>

                <FormItem style="position: absolute;right: 30px">
                    <FormItem>
                        <Button @click="add()">
                            <Icon type="ios-add-circle-outline"/>
                            添加商品
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
            <Form ref="formValidate" :model="product" :label-width="80">
                <FormItem label="商品编号" prop="pId">
                    <Input v-model="product.pId" :maxlength=18 placeholder="请输入商品编号"></Input>
                </FormItem>
                <FormItem label="商品名称" prop="pName">
                    <Input v-model="product.pName" placeholder="请输入商品名称"></Input>
                </FormItem>
                <FormItem label="商品类型" prop="tId">
                    <Select v-model="product.tId" placeholder="请选择商品类型">>
                        <Option v-for="item in productType" :value="item.tId" :key="item.tId">{{ item.tName }}</Option>
                    </Select>
                </FormItem>
                <FormItem label="商品价格" prop="pPrice">
                    <Input v-model="product.pPrice" placeholder="请输入商品价格"></Input>
                </FormItem>
                <FormItem label="商品状态" prop="pStatus">
                    <RadioGroup v-model="product.pStatus">
                        <Radio label="上架中">上架中</Radio>
                        <Radio label="已下架">已下架</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem label="库存数量" prop="pNumber">
                    <InputNumber :min="1" v-model="product.pNumber"></InputNumber>
                </FormItem>
                <FormItem label="商品照片" prop="pImg">
                    <div style="width: 28%;position:relative;float: left;margin: auto; text-align: center;">
                        <Upload name='file' :show-upload-list='false' :on-success='resultMsg'
                                action="http://127.0.0.1:8081/florist/upload/memberInformation">
                            <p><img v-if="product.pImg"
                                    :src="'http://127.0.0.1:8081/florist/upload/getPhoto?url='+encodeURIComponent(product.pImg)"
                                    width="200" height="250" style="border:1px solid #d3d3d3;"/></p>
                            <Button icon="ios-cloud-upload-outline">可拖动上传</Button>
                        </Upload>
                    </div>
                </FormItem>
                <FormItem label="商品描述" prop="pDetailed">
                    <Input v-model="product.pDetailed" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
                           placeholder="请输入商品描述"></Input>
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
                productType: [],
                modal14loading: true,
                title: '添加商品',
                count: 10,
                columns7: [{
                    title: '商品编号',
                    key: 'pId',
                    align: 'center',
                    width: 150,
                    tooltip: true
                }, {
                    title: '商品名称',
                    key: 'pName',
                    align: 'center',
                    width: 250,
                    tooltip: true
                },
                    {
                        title: '图片',
                        key: 'pName',
                        align: 'center',
                        width: 250,
                        tooltip: true,
                        render: (h, params) => {
                            return h('img', {
                                style: {//设置样式
                                    width: '100px',
                                    'height': '80px',
                                    'border-radius': '5%'
                                },
                                attrs: {//设置属性
                                    src: 'http://127.0.0.1:8081/florist/upload/getPhoto?url=' + encodeURIComponent(params.row.pImg)
                                }
                            });

                        }
                    },
                    {
                        title: '价格',
                        key: 'pPrice',
                        align: 'center'
                    },
                    {
                        title: '状态',
                        key: 'pStatus',
                        align: 'center',
                    }, {
                        title: '库存数量',
                        key: 'pNumber',
                        tooltip: true,
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
                                            this.remove(params.row.pId)
                                        }
                                    }
                                }, '移除')
                            ]);
                        }
                    }
                ],
                data6: [],
                pId: "",
                pName: '',
                product: {
                    pId: "",
                    pName: '',
                    tId: '',
                    pImg: '',
                    pPrice: '',
                    pStatus: "上架中",
                    pNumber: 1,
                    pDetailed: '',
                },
            }
        },
        methods: {
            //单击添加
            add() {
                this.title = "新增商品";
                this.product = {
                    pId: "",
                    pName: '',
                    tId: '',
                    pImg: '',
                    pPrice: '',
                    pStatus: "上架中",
                    pNumber: 1,
                    pDetailed: '',
                };
                this.modal14 = true;
            },
            //上传文件
            resultMsg(res) {
                if (res.code === 1224) {
                    this.product.pImg = res.data;
                    this.$Message.success(res.message);
                } else {
                    this.$Message.error(res.message);
                }
            },
            //单击编辑
            show(data) {
                this.title = '编辑商品'
                this.modal14 = true;
                this.product = JSON.parse(JSON.stringify(data));
            },
            //弹出添加保存
            addok() {
                let th = this;
                var urls = "insert";
                if (this.title == "编辑商品") {
                    urls = "updateByPrimaryKey";
                }
                axios.post('/florist/product/' + urls, th.product, {
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
                        axios.get('/florist/product/deleteByPrimaryKey', {
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
                    filename: '商品信息'
                });
            },
            changePage(page) {
                const th = this;
                th.loading = true;
                if (!th.pId) {
                    th.pId = '';
                }
                if (!th.pName) {
                    th.pName = '';
                }
                axios.get('/florist/product/selectPage', {
                    params: {
                        page: page,
                        pId: th.pId,
                        pName: th.pName
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
            let th = this;
            axios.get('/florist/productType/selectAll').then(function (res) {
                th.productType = res.data.data;
            })
        }
    }
</script>
