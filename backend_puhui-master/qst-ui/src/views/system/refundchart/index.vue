<template>
  <div class="app-container">
	<el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
	  <el-form-item label="还款时间" prop="refTime">
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
    	<div id="myChart" :style="{width: '1000px',height: '400px'}"></div>


    </div>
  </div>
</template>

<script>
import { listRefund} from "@/api/system/refundchart";
import {getAptitudeCompanyName} from "@/api/system/aptitude";
import {getBankNames, getLoansNo} from "@/api/system/loans";

export default {
  name: "Refund",
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
      // 还款表格数据
      refundList: [],
      // 还款状态
      refundStatus: [],
      //放款方式
      bankSendModes: [],
      //贷款编号
      loansNoOptions: [],
      //企业名称
      companiesName: [],
      //银行
      bankNames:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        refComNo: null,
        refBankId: null,
        refLoId: null,
        refOveId: null,
        refTime: null,
        refMoney: null,
        refMode: null,
        refState: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
	  //x坐标
	  xData:[],
	  //y坐标
	  yData:[],
	  //开始时间
	  startTime:"",
	  //结束时间
	  endTime:""
    };
  },
  created() {
    this.getList();

  },
  methods: {
    /** 查询还款列表 */
    getList() {
	  this.xData=[];
	  this.yData=[];

	  var startTime = new Date(this.startTime);
	  var endTime = new Date(this.endTime);
      this.loading = true;
      listRefund(this.queryParams).then(response => {
        this.refundList = response.rows;

		if(this.startTime!=""&&this.endTime!=""){
			var data = [];
			for(var i=0;i<this.refundList.length;i++){//如果时间不在范围内，则不加入data
				var time = new Date(this.refundList[i].refTime);

				if(time>=startTime&&time<=endTime){
					data.push(this.refundList[i]);
				}
			}
			this.refundList = data;//将筛选后的数据存入lonsList
		}

            this.xData.push(this.refundList[0].refTime)
            this.yData.push(parseInt(this.refundList[0].refMoney))
            for(var i=1;i<this.refundList.length;i++){

            	var time = this.refundList[i].refTime;
        	if(time!=null){
        		time = time.substring(0,10);
        	}
            	var money = parseInt(this.refundList[i].refMoney);


            		if(time == ((this.refundList[i-1].refTime==null)?null:(this.refundList[i-1].refTime.substring(0,10)))){
            			this.yData[this.yData.length-1] += money;
            		}else{
            			this.xData.push(time);
            			this.yData.push(money);
            		}

            }
        console.log(this.xData);
        console.log(this.yData);

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
    		  legend: {
            data:['还款数据金额']
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
    		    type: 'value',
    		    name: '单位（元）'
    		  },
    		  //显示标题
    		title: {
    		  	text: "还款统计"
    		  	},
    		  series: [
    		    {
    		      name: '还款数据金额',
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
};
</script>
