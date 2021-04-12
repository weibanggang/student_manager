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
				<FormItem style="width: 600px">
					<Row>
						<Col span="4" style="text-align: center;">
							课程/时间
						</Col>
						<Col span="20">
							<Select v-model="courseName" placeholder="课程/时间模糊查询" filterable @on-change="changePage($event)">
								<Option v-for="item in courseNameList" :value="item.uuid" :key="item.uuid">{{ item.courseName + "（" + item.startTime+ " - " + item.endTime+ "）"}}</Option>
							</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem style="width: 200px">
					<Row>
						<Col span="8" >
							出勤模式
						</Col>
						<Col span="16">
								<RadioGroup v-model="situation">
									<Radio label="签到">签到</Radio>
									<Radio label="签退">签退</Radio>
								</RadioGroup>
						</Col>
					</Row>
				</FormItem>

				<FormItem style="position: absolute;right: 30px">
					<FormItem>
						<Button @click="add()" style="width: 100px">
							提交
						</Button>
					</FormItem>
				</FormItem>
			</Form>
		</div>

		<Table border :columns="Columns7" :data="data6" height="450" :loading="loading" stripe size='default'
			   ref="table" no-data-text="请选择课程"></Table>
		<!--<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>-->

	</div>
</template>
<script>
	export default {
		data() {
			return {
				loading: false,
				situation:"签到",
				count: 10,
				Columns7: [
					{
						title: '学号',
						key: 'studentId',
						align: 'center',
						width: 200
					}, {
						title: '姓名',
						key: 'studentName',
						align: 'center',
					},
					{
						title: '状态',
						key: 'sex',
						align: 'center',
						width: 400,
						render: (h, params) => {
							console.log(params.row)
							return h('div', [
								h('Radio', {
									props: {
										label: "正常",
										value:params.row.status === "正常"
									},
									on: {
										'on-change': () => {
											this.data6[params.index].status = "正常";
											params.row.status = "正常"
										}
									}
								},"正常"),
								h('Radio', {
									props: {
										label: "缺勤",
										value:params.row.status === "缺勤"
									},
									on: {
										'on-change': () => {
											this.data6[params.index].status = "缺勤";
											params.row.status = "缺勤"
										}
									}
								},"缺勤"),
							])
						}
						}
				],
				data6: [],
				courseName: "",
				studentId: "",
				classaList: [],
				studentCheck:[],
				teacherCheck:[],
				courseNameList:[],
			}
		},
		methods: {
			//单击添加
			add() {
				console.log(this.data6);
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
					th.data6 = [];
					res.data.data.forEach((params)=>{
						let studentCheck = {
							studentId:params.studentId,
							studentName:params.name,
							status:"正常",
						};
						th.data6.push(studentCheck);
					})
				})
				th.loading = false;
			},
		},
		created() {
			axios.get('/student_manager/classScheduleCard/selectPage', {
				params: {
					limit:1000,
					studentId: "1617842546979"
				}
			}).then((res) => {
				this.courseNameList = res.data.data;
			})


		}
	}
</script>
