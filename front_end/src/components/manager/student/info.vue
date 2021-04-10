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
			<Form ref="student" inline>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
							学生姓名
						</Col>
						<Col span="16">
							<Input height="20" placeholder="学生姓名模糊查询" v-model="name" icon="ios-search"
								   @on-click="changePage(1)"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
							学号
						</Col>
						<Col span="16">
							<Input height="20" placeholder="学号模糊查询" v-model="studentId" icon="ios-search"
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

		<Modal class-name="vertical-center-modal" v-model="modal14" fullscreen  :loading="modal14loading" width="800px" scrollable
			   :title="title" @on-ok="addok">
			<Form ref="forms" :model="student" :rules="rule" :label-width="120">
				<Row>
					<Col span="8">
						<FormItem label="学号" prop="studentId">
							<Input v-model="student.studentId" disabled></Input>
						</FormItem>
					</Col>

					<Col span="8">
						<FormItem label="姓名" prop="name">
							<Input v-model="student.name" :maxlength=18 placeholder="请输入名字"></Input>
						</FormItem>
					</Col>
					<Col span="8">
						<FormItem label="性别" prop="sex">
							<RadioGroup v-model="student.sex">
								<Radio label="男">男</Radio>
								<Radio label="女">女</Radio>
							</RadioGroup>
						</FormItem>
					</Col>

					<Col span="8">
						<FormItem label="出生日期" prop="sentrancedate">
							<DatePicker type="date" format="yyyy-MM-dd" @on-change="student.sentrancedate=$event"
										:value="student.sentrancedate" placeholder="请选择出生日期"
										style="width: 100%"></DatePicker>
						</FormItem>
					</Col>
					<Col span="8">
						<FormItem label="手机号码" prop="number">
							<Input v-model="student.number" maxlength="11" placeholder="请输入手机号码"></Input>
						</FormItem>
					</Col>
					<Col span="8">
						<FormItem label="紧急联系人" prop="parentsName">
							<Input v-model="student.parentsName"  placeholder="请输入紧急联系人姓名"></Input>
						</FormItem>
					</Col>
					<Col span="8">
						<FormItem label="紧急联系号码" prop="parentsNumber">
							<Input v-model="student.parentsNumber" :maxlength=11  placeholder="请输入紧急联系号码"></Input>
						</FormItem>
					</Col>
					<Col span="8">
						<FormItem label="入学时间" prop="sentrancedate">
							<DatePicker type="date" format="yyyy-MM-dd" @on-change="student.birthday=$event"
										:value="student.birthday" placeholder="请选择出生日期"
										style="width: 100%"></DatePicker>
						</FormItem>
					</Col>
					<Col span="8">
						<FormItem label="邮箱地址" prop="qq">
							<Input v-model="student.qq" maxlength="30" placeholder="请输入邮箱地址"></Input>
						</FormItem>
					</Col>

					<Col span="8">
						<FormItem label="状态" prop="state">
							<RadioGroup v-model="student.state">
								<Radio label="在读">在读</Radio>
								<Radio label="休学">休学</Radio>
								<Radio label="毕业">毕业</Radio>
							</RadioGroup>
						</FormItem>
					</Col>
					<Col span="8">
						<FormItem label="学员等级" prop="grade">
							<RadioGroup v-model="student.grade">
								<Radio label="临时听课">临时听课</Radio>
								<Radio label="VIP学员">VIP学员</Radio>
							</RadioGroup>
						</FormItem>
					</Col>
					<Col span="8">
						<FormItem label="招生老师" prop="admissionsTeacher">
							<Input v-model="student.admissionsTeacher" maxlength="100" placeholder="招生老师"></Input>
						</FormItem>
					</Col>
				</Row>
				<Row>
					<Col span="24">
						<FormItem label="班级" prop="classs">
							<Select v-model="student.classs" placeholder="请选择班级"  filterable>
								<Option v-for="item in classaList" :value="item.uuid" :key="item.uuid">{{ item.className}}</Option>
							</Select>
						</FormItem>
					</Col>
				</Row>
				<Row>
					<Col span="24">
						<FormItem label="家庭住址" prop="address">
							<Input v-model="student.address" maxlength="100" placeholder="请输入家庭住址"></Input>
						</FormItem>
					</Col>
				</Row>
				<FormItem label="备注" prop="remarks">
					<Input v-model="student.remarks" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
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
						title: '学号',
						key: 'studentId',
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
						key: 'number',
						align: 'center'
					},
					{
						title: '状态',
						key: 'state',
						align: 'center',
					},
					{
						title: '等级',
						key: 'grade',
						align: 'center',
					},
					{
						title: '入学时间',
						key: 'sentrancedate',
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
											this.remove(params.row.studentId)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				name: "",
				studentId: "",
				classaList: [],
				student: {
					studentId: new Date().getTime(),
					name: '',
					sex: '男',
					classs: '',
					birthday:'',
					parentsNumber:'',
					parentsName: '',
					number:'',
					qq:'',
					grade: 'VIP学员',
					phone: '',
					sentrancedate: '',
					address: "",
					state: '在读',
					admissionsTeacher:'',
					remarks: ''
				},
				rule: {
					name: [
						{required: true, message: '请输入姓名', trigger: 'change'},
					],
					classs: [
						{required: true, message: '请选择班级', trigger: 'change'},
					],
					birthday: [
						{required: true, message: '请选择出生日期', trigger: 'change'},
					],
					parentsNumber: [
						{required: true, message: '请输入紧急联系号码', trigger: 'change'},
					],
					number: [
						{required: true, message: '请输入手机号码', trigger: 'change'},
					],
					sentrancedate: [
						{required: true, message: '请选择入学时间', trigger: 'change'},
					],
					parentsName: [
						{required: true, message: '请输入身份证', trigger: 'change'},
					],
					admissionsstudent: [
						{required: true, message: '请输入招生老师', trigger: 'change'},
					],
					address: [
						{required: true, message: '请输入家庭住址', trigger: 'change'},
					]
				}
			}
		},
		methods: {
			//单击添加
			add() {
				this.title = "新增";
				this.student = {
					studentId: new Date().getTime(),
					name: '',
					sex: '男',
					classs: '',
					birthday:'',
					parentsNumber:'',
					parentsName: '',
					number:'',
					qq:'',
					grade: 'VIP学员',
					phone: '',
					sentrancedate: '',
					address: "",
					state: '在读',
					admissionsTeacher:'',
					remarks: ''
				};
				this.modal14 = true;
			},
			//单击编辑
			show(data) {
				this.title = '编辑'
				this.modal14 = true;
				console.log(data)
				this.student = JSON.parse(JSON.stringify(data));
			},
			//弹出添加保存
			addok() {
				console.log(this.student)
				this.$refs["forms"].validate((valid) => {
					if (valid) {
						let th = this;
						var urls = "insert";
						if (this.title == "编辑") {
							urls = "updateByPrimaryKey";
						}
						axios.post('/student_manager/student/' + urls, th.student, {
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
						axios.get('/student_manager/student/deleteByPrimaryKey', {
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
					filename: '学生信息'
				});
			},
			changePage(page) {

				this.loading = true;
				if (!this.name) {
					this.name = '';
				}
				if (!this.student_name) {
					this.student_name = '';
				}
				const th = this;
				axios.get('/student_manager/student/selectPage', {
					params: {
						page: page,
						name: th.name,
						studentId: th.studentId
					}
				}).then(function (res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				th.loading = false;
			},
			init(){
				axios.get('/student_manager/classa/selectAll').then( (res)=> {
					this.classaList = res.data.data;
				})
			},
		},
		created() {
			this.init();
			this.changePage(1);
		}
	}
</script>
