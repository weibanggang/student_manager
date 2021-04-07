<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
			<div class="rigtop">
				<Form ref="users" inline>
					<FormItem >
						<Row>
							<Col span="8" style="text-align: center;">
								名字查询
							</Col>
							<Col span="16">
							<Input height="20" placeholder="名字查询模糊查询" v-model="uName"  icon="ios-search" @on-click="changePage(1)"></Input>
							</Col>
						</Row>
					</FormItem>
					<FormItem>
						<Row>
							<Col span="8" style="text-align: center;">
								手机号码
							</Col>
							<Col span="16">
								<Input height="20" placeholder="手机号码模糊查询" v-model="uTel" icon="ios-search" @on-click="changePage(1)"></Input>
							</Col>
						</Row>
					</FormItem>

					<FormItem style="position: absolute;right: 30px">
						<FormItem>
							<Button @click="add()">
								<Icon type="ios-add-circle-outline" />添加记录
							</Button>
						</FormItem>
						<Button @click="exportData()">
							<Icon type="ios-download-outline" />数据导出
						</Button>
					</FormItem>
				</Form>
			</div>



		<Table border :columns="columns7" :data="data6" height="450" :loading="loading" stripe size='default' ref="table"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		
		<Modal v-model="modal14" :loading="modal14loading" scrollable :title="title" @on-ok="addok">
			<Form ref="formValidate" :model="users" :label-width="80">
				<FormItem label="登录名" prop="uName">
					<Input v-model="users.uNames" :maxlength=18 placeholder="请输入登录名"></Input>
				</FormItem>
				<FormItem label="密码" prop="uPwd">
					<Input type="password" v-model="users.uPwd"></Input>
				</FormItem>
				<FormItem label="姓名" prop="uName">
					<Input v-model="users.uName" :maxlength=11 placeholder="请输入姓名"></Input>
				</FormItem>
				<FormItem label="状态" prop="uStatus">
					<RadioGroup v-model="users.uStatus">
						<Radio label="正常">正常</Radio>
						<Radio label="冻结">冻结</Radio>
					</RadioGroup>
				</FormItem>
				<FormItem label="手机号码" prop="uTel">
					<Input v-model="users.uTel" maxlength="11"></Input>
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
				title:'添加管理员',
				count: 10,
				columns7: [{
					title: '编号',
					key: 'uId',
					align: 'center',
					width: 100
				},{
						title: '管理员名',
						key: 'uNames',
						align: 'center',
					},
					{
						title: '姓名',
						key: 'uName',
						align: 'center',
					},
					{
						title: '手机号码',
						key: 'uTel',
						align: 'center'
					},
					{
						title: '状态',
						key: 'uStatus',
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
											this.remove(params.row.uId)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				uName:"",
				uTel:"",
				users: {
					uId: "",
					uName: '',
					uNames: '',
					uPwd: '',
					uTel: '',
					uType: '0',
					uStatus: "正常",
				},
			}
		},
		methods: {
			//单击添加
			add() {
				this.title = "新增管理员";
				this.users= {
					uId: "",
					uName: '',
					uNames: '',
					uPwd: '',
					uTel: '',
					uType: '0',
					uStatus: "正常",
				};
				this.modal14 = true;
			},
			//单击编辑
			show(data){
				this.title = '编辑管理员'
				this.modal14 = true;
				this.users = JSON.parse(JSON.stringify(data));
			},
			//弹出添加保存
			addok() {
				let th = this;
				var urls = "insert";
				if(this.title == "编辑管理员"){
					urls = "updateByPrimaryKey";
				}
				axios.post('/florist/users/'+urls, th.users, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
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
			remove(id){
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get('/florist/users/deleteByPrimaryKey', {
							params: {
								id: id
							}
						}).then(function(res) {
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
					filename: '管理员信息'
				});
			},
			changePage(page) {
				const th = this;
				th.loading = true;
				if(!th.uName){
					th.uName = '';
				}
				if(!th.uTel){
					th.uTel='';
				}
				axios.get('/florist/users/selectPage', {
					params: {
						page: page,
						uTel: th.uTel,
						uName: th.uName,
						type:0,
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				th.loading=false;
			},
		},
		created() {
			this.changePage(1);
		}
	}
</script>
