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
							<Select v-model="uuid" placeholder="课程/时间模糊查询" filterable @on-change="changePage($event)">
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
							return h('div', [
								h('Radio', {
									props: {
										label: "已签到",
										value:params.row.status === "已签到"
									},
									on: {
										'on-change': () => {
											this.data6[params.index].status = "已签到";
											params.row.status = "已签到"
										}
									}
								},"正常"),
								h('Radio', {
									props: {
										label: "未签到",
										value:params.row.status === "未签到"
									},
									on: {
										'on-change': () => {
											this.data6[params.index].status = "未签到";
											params.row.status = "未签到"
										}
									}
								},"缺勤"),
							])
						}
						}
				],
				data6: [],
				obj:"",
				courseName: "",
				courseId: "",
				uuid:"",
				studentId: "",
				classaList: [],
				studentCheck:[],
				teacherCheck:{},
				courseNameList:[],
				courseNameSETime:"",
			}
		},
		methods: {
			//单击添加
			add() {
				let ok = 0;
				this.data6.forEach((res)=>{
					if(res.status == "正常"){
						ok++;
					}
				})

				this.teacherCheck={
					teacherId:"1617883891698",
					courseId:this.obj.courseId,
					courseNameSETime:this.courseNameSETime,
					numberOfPeople:this.data6.length,
					numberOfReal:ok,
					numberOfAbsence:this.data6.length - ok
				}
				let th =this;
				this.data6.forEach((d)=>{
					axios.post('/student_manager/studentCheck/insert' , d, {
						headers: {
							"Content-Type": "application/json;charset=utf-8"
						}
					}).then(function (res) {
						if (res.data.code === 200) {
							console.log(ok)
						}
					})
				})
				axios.post('/student_manager/teacherCheck/insert' , this.teacherCheck, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function (res) {
					if (res.data.code === 200) {
						th.$Message.success("提交成功！")
						setTimeout(()=>{
							window.location.href = "/#/manager/";
						},500)
					}
				})
			},
			changePage(uuid) {
				this.courseNameList.forEach((item)=>{
					if(item.uuid === uuid){
						this.obj = item;
						this.courseNameSETime = item.courseName + "（" + item.startTime+ " - " + item.endTime+ "）";
						this.courseId = item.courseId;
					}
				})
				this.loading = true;
				let th = this;
				axios.get('/student_manager/studentCheck/selectByClassa', {
					params: {
						id: this.courseId,
						courseNameSETime: this.courseNameSETime
					}
				}).then(function (res) {
					th.data6 = res.data.data;
				})
				th.loading = false;
			},
		},
		created() {
			axios.get('/student_manager/classScheduleCard/selectPage', {
				params: {
					limit:1000,
					teacherId: "1617883891698"
				}
			}).then((res) => {
				this.courseNameList = res.data.data;
			})


		}
	}
</script>
