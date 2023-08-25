<template>
  <div class="app-container">
     <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
       
       <el-form-item label="申请时间" prop="loaTime">
         <el-date-picker clearable size="small"
           v-model="startTime"
           type="date"
           value-format="yyyy-MM-dd"
           placeholder="选择开始时间">
         </el-date-picker>
		 至
		 <el-date-picker clearable size="small"
		   v-model="endTime"
		   type="date"
		   value-format="yyyy-MM-dd"
		   placeholder="选择结束时间">
		 </el-date-picker>
		 
       </el-form-item>
	   
       
       <el-form-item>
         <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
         <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
       </el-form-item>
     </el-form>
     
     
	<!-- 实现ECharts -->
	<h3>实现ECharts</h3>
		<div id="myChart" :style="{width: '1500px',height: '700px'}"></div>

	
	</div>
	
	
	
</template>

<script>
import {
  listLoans
} from "@/api/system/loanschart";
import {getAptitudeCompanyName} from "@/api/system/aptitude";
import {getDetailsCompanyName} from "@/api/system/details";

	export default{
		name: 'Home',
		data() {
			return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 贷款表格数据
      loansList: [],
      //企业名称
      companiesName: [],
      //银行列表
      bankNames: [],
      //贷款状态
      sysLoaStatus: [],
      loansNoOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: null,
        pageSize: null,
        loaNo: null,
        loaComNo: null,
        loaBankId: null,
        loaLisId: null,
        loaUse: null,
        loaIntegral: null,
        loaRate: null,
        loaState: null,
        loaTime: null,
        loaMoney: null,
        loaEndTime: null,
      },
	  //开始时间
	  startTime:"",
	  //结束时间
	  endTime:"",
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
	  //x坐标
	  xData:[],
	  //y坐标
	  yData:[]
    };
  },
		created(){
			this.getList();
			
		},
		methods:{
			/** 查询贷款列表 */
			getList() {
				this.xData=[];
				this.yData=[];
				
				var startTime = new Date(this.startTime);
				var endTime = new Date(this.endTime);
				
			  this.loading = true;
			  listLoans(this.queryParams).then(response => {
				this.loansList = response.rows;
				
				if(this.startTime!=""&&this.endTime!=""){
					var data = [];
					for(var i=0;i<this.loansList.length;i++){//如果时间不在范围内，则不加入data
						var time = new Date(this.loansList[i].loaTime);
						
						if(time>=startTime&&time<=endTime){
							data.push(this.loansList[i]);
						}
					}
					this.loansList = data;//将筛选后的数据存入lonsList
				}
				
				
				
				if(this.loansList.length!=0){
					this.xData.push(this.loansList[0].loaTime.substring(0,10))
					this.yData.push(this.loansList[0].loaMoney)
					for(var i=1;i<this.loansList.length;i++){
						var time = this.loansList[i].loaTime.substring(0,10);
						var money = this.loansList[i].loaMoney;
						
							if(time == this.loansList[i-1].loaTime.substring(0,10)){
								this.yData[this.yData.length-1] += money;
							}else{
								this.xData.push(time);
								this.yData.push(money);
							}
						
					}
				}
				
				this.Draw();
				
			  });
			  
			},
			Draw(){
				let myChart = this.$echarts.init(document.getElementById('myChart'))
				
				
				myChart.setOption({
					color: ['#0000ff'],
					tooltip: {
					    trigger: 'axis',
					    axisPointer: {
					      type: 'shadow'
					    }
					  },
					  grid: {
					    left: '3%',
					    right: '4%',
					    bottom: '3%',
					    containLabel: true
					  },
					 xAxis: {
					    type: 'category',
					    data: this.xData
					  },
					  yAxis: {
					    type: 'value'
					  },
					  //显示标题
					title: {
					  	text: "贷款申请统计"
					  	},
					  series: [
					    {
					      data: this.yData,
					      type: 'bar',
					      showBackground: true,
					      backgroundStyle: {
					        color: 'rgba(180, 180, 180, 0.2)'
					      }
					    }
					  ]
				})
			},
			
			
			/** 搜索按钮操作 */
			handleQuery() {
				this.getList();
			},
			/** 重置按钮操作 */
			resetQuery() {
			  this.startTime="";
			  this.endTime="";
			},
			
			
		}
	}
</script>
