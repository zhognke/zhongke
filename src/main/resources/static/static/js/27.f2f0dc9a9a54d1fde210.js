webpackJsonp([27],{"8Un9":function(e,t,l){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=l("bOdI"),s=l.n(a),i=l("Wxq9"),o={name:"form3",data:function(){var e;return e={indus:"",city1:"",remark:"",subjectYear:{startDate:"",endDate:""},socialResults:{energySaving:"",co2EmissionReduction:"",waterConservation:"",wasteUtilization:"",codEmissionReduction:"",ammoniaNitrogenEmissionReduction:"",noxEmissionReduction:"",vocsEmissionReduction:"",so2EmissionReduction:"",otherEmissionReduction:""},expectedReturn:{newSalesRevenue:"",newProfits:"",newTax:"",newForex:""},option6:[{value:"电力装备",label:"电力装备"},{value:"轨道交通装备",label:"轨道交通装备"},{value:"节能和新能源汽车",label:"节能和新能源汽车"},{value:"节能环保",label:"节能环保"},{value:"农林和工程机械",label:"农林和工程机械"},{value:"生物医药和高性能医疗器械",label:"生物医药和高性能医疗器械"},{value:"新材料",label:"新材料"},{value:"新一代电子信息",label:"新一代电子信息"},{value:"智能家电",label:"智能家电"},{value:"智能装备",label:"智能装备"},{value:"其他",label:"其他"}],total:{totalInvestment:"",investmentSelfPrepare:"",investmentLoans:"",investmentUsingForex:""},advancedManufacturingType:"",baseForm:{companyName:"",projectName:"",projectContent:"",province:"",city:"",district:"",projectType:"",projectTypeDetail:""},value3:"",value4:"",input:"",o2Value:"",option:[{value:"农、林、木、鱼业",label:"农、林、木、鱼业",children:[{value:"农业",label:"农业"},{value:"林业",label:"林业"},{value:"畜牧业",label:"畜牧业"},{value:"渔业",label:"渔业"},{value:"农林牧渔服务业",label:"农林牧渔服务业"}]},{value:"采矿业",label:"采矿业",children:[{value:"煤炭开采洗选业",label:"煤炭开采洗选业"},{value:"石油和天然气开采业",label:"石油和天然气开采业"},{value:"黑色金属矿开采选业",label:"黑色金属矿开采选业"},{value:"有色金属矿选采业",label:"有色金属矿选采业"},{value:"非金属矿采选业",label:"非金属矿采选业"},{value:"开采辅助活动",label:"开采辅助活动"},{value:"其他采矿业",label:"其他采矿业"}]},{value:"制造业",label:"制造业",children:[{value:"农副食品加工",label:"农副食品加工"},{value:"食品制造业",label:"食品制造业"},{value:"酒、饮料和精制茶制造业",label:"酒、饮料和精制茶制造业"},{value:"烟草制品业",label:"烟草制品业"},{value:"纺织业",label:"纺织业"},{value:"纺织服装、服饰业",label:"纺织服装、服饰业"},{value:"皮革、毛皮、羽毛及其制品和制鞋业",label:"皮革、毛皮、羽毛及其制品和制鞋业"},{value:"木材加工和木、竹、藤棕、草制品业",label:"木材加工和木、竹、藤棕、草制品业"},{value:"家具制造业",label:"家具制造业"},{value:"造纸和纸制品业",label:"造纸和纸制品业"},{value:"印刷和记录媒介复制业",label:"印刷和记录媒介复制业"},{value:"文教、工美、体育和娱乐用品制造业",label:"文教、工美、体育和娱乐用品制造业"},{value:"石油加工、炼焦和核燃料加工业",label:"石油加工、炼焦和核燃料加工业"},{value:"化学燃料和化学制品制造业",label:"化学燃料和化学制品制造业"},{value:"医药制造业",label:"医药制造业"},{value:"化学纤维制造业",label:"化学纤维制造业"},{value:"橡胶和塑料制品业",label:"橡胶和塑料制品业"},{value:"非金属矿物制品业",label:"非金属矿物制品业"},{value:"黑色金属冶炼和压延加工业",label:"黑色金属冶炼和压延加工业"},{value:"有色金属冶炼和压延加工业",label:"有色金属冶炼和压延加工业"},{value:"金属制品业",label:"金属制品业"},{value:"通用设备制造业",label:"通用设备制造业"},{value:"专用设备制造业",label:"专用设备制造业"},{value:"汽车制造业",label:"汽车制造业"},{value:"铁路、船舶、航空航天和其他运输设备制造业",label:"铁路、船舶、航空航天和其他运输设备制造业"},{value:"电器机械和器材制造业",label:"电器机械和器材制造业"},{value:"计算机、通信和其他电子设备制造业",label:"计算机、通信和其他电子设备制造业"},{value:"仪器仪表制造业",label:"仪器仪表制造业"},{value:"废弃资源综合利用业",label:"废弃资源综合利用业"},{value:"金属制品、机械和设备修理业",label:"金属制品、机械和设备修理业"}]},{value:"水利环境公共设施管理",label:"水利环境公共设施管理",children:[{value:"水利管理业",label:"水利管理业"},{value:"生态保护和环境治理业",label:"生态保护和环境治理业"},{value:"公共设施管理业",label:"公共设施管理业"}]},{value:"居民服务、修理和其他",label:"居民服务、修理和其他",children:[{value:"居民服务业",label:"居民服务业"},{value:"机动车、电子产品和日用产品修理业",label:"机动车、电子产品和日用产品修理业"},{value:"其他服务业",label:"其他服务业"}]},{value:"教育",label:"教育",children:[{value:"教育",label:"教育"}]},{value:"卫生和社会工作",label:"卫生和社会工作",children:[{value:"卫生",label:"卫生"},{value:"社会工作",label:"社会工作"}]},{value:"文化、体育和娱乐业",label:"文化、体育和娱乐业",children:[{value:"新闻和出版业",label:"新闻和出版业"},{value:"广播、电视、电影和影视录音制作业",label:"广播、电视、电影和影视录音制作业"},{value:"文化艺术业",label:"文化艺术业"},{value:"体育",label:"体育"},{value:"娱乐业",label:"娱乐业"}]},{value:"公共管理保障组织",label:"公共管理保障组织",children:[{value:"中国共产党机关",label:"中国共产党机关"},{value:"国家机构",label:"国家机构"},{value:"人民政协、民主党派",label:"人民政协、民主党派"},{value:"社会保障",label:"社会保障"},{value:"群众团体、社会团体和其他成员组织",label:"群众团体、社会团体和其他成员组织"},{value:"基层群众自治组织",label:"基层群众自治组织"}]},{value:"国际组织",label:"国际组织",children:[{value:"国际组织",label:"国际组织"}]},{value:"电力热力燃气及水生产供应",label:"电力热力燃气及水生产供应",children:[{value:"电力、热力生产和供应业",label:"电力、热力生产和供应业"},{value:"燃气生产和供应业",label:"燃气生产和供应业"},{value:"水的生产和供应业",label:"水的生产和供应业"}]},{value:"建筑业",label:"建筑业",children:[{value:"房屋建筑业",label:"房屋建筑业"},{value:"土木工程建筑业",label:"土木工程建筑业"},{value:"建筑安装业",label:"建筑安装业"},{value:"建筑装饰和其他建筑业",label:"建筑装饰和其他建筑业"}]},{value:"批发和零售业",label:"批发和零售业",children:[{value:"批发业",label:"批发业"},{value:"零售业",label:"零售业"}]},{value:"交通运输、仓储和邮政业",label:"交通运输、仓储和邮政业",children:[{value:"铁路运输业",label:"铁路运输业"},{value:"道路运输业",label:"道路运输业"},{value:"水上运输业",label:"水上运输业"},{value:"航空运输业",label:"航空运输业"},{value:"管道运输业",label:"管道运输业"},{value:"装卸搬运和运输代理业",label:"装卸搬运和运输代理业"},{value:"仓储业",label:"仓储业"},{value:"邮政业",label:"邮政业"}]},{value:"住宿和餐饮业",label:"住宿和餐饮业",children:[{value:"住宿业",label:"住宿业"},{value:"餐饮业",label:"餐饮业"}]},{value:"信息传输软件和信息技术",label:"信息传输软件和信息技术",children:[{value:"电信、广播电视和卫星传输服务",label:"电信、广播电视和卫星传输服务"},{value:"互联网和相关服务",label:"互联网和相关服务"},{value:"软件和信息技术服务业",label:"软件和信息技术服务业"}]},{value:"金融业",label:"金融业",children:[{value:"货币金融服务",label:"货币金融服务"},{value:"资本市场服务",label:"资本市场服务"},{value:"保险业",label:"保险业"},{value:"其他金融业",label:"其他金融业"}]},{value:"房地产业",label:"房地产业",children:[{value:"房地产业",label:"房地产业"}]},{value:"租赁和商务服务业",label:"租赁和商务服务业",children:[{value:"租赁业",label:"租赁业"},{value:"商务服务业",label:"商务服务业"}]},{value:"科学研究和技术服务业",label:"科学研究和技术服务业",children:[{value:"研究和试验发展",label:"研究和试验发展"},{value:"专业技术服务业",label:"专业技术服务业"},{value:"科技推广和应用服务业",label:"科技推广和应用服务业"}]}],options3:[{value:"选项1",label:"黄金糕"},{value:"选项2",label:"双皮奶"},{value:"选项3",label:"蚵仔煎"},{value:"选项4",label:"龙须面"},{value:"选项5",label:"北京烤鸭"}],options:i.regionData,selectedOptions:"",options2:[{value:"工业转型升级技术改造项目",label:"工业转型升级技术改造项目",children:[{value:"续建项目",label:"续建项目",children:[{value:"培育发展高端制造业项目",label:"培育发展高端制造业项目",children:[{value:"新一代电子信息",label:"新一代电子信息"},{value:"高端设备",label:"高端设备"},{value:"智能家电",label:"智能家电"},{value:"新能源汽车",label:"新能源汽车"},{value:"新材料",label:"新材料"},{value:"节能环保",label:"新材料"}]},{value:"改造提升传统产业项目",label:"改造提升传统产业项目",children:[{value:"新一代电子信息",label:"新一代电子信息"},{value:"高端设备",label:"高端设备"},{value:"智能家电",label:"智能家电"},{value:"新能源汽车",label:"新能源汽车"},{value:"新材料",label:"新材料"},{value:"节能环保",label:"新材料"}]}]},{value:"新开项目",label:"新开项目",children:[{value:"培育发展高端制造业项目",label:"培育发展高端制造业项目",children:[{value:"新一代电子信息",label:"新一代电子信息"},{value:"高端设备",label:"高端设备"},{value:"智能家电",label:"智能家电"},{value:"新能源汽车",label:"新能源汽车"},{value:"新材料",label:"新材料"},{value:"节能环保",label:"新材料"}]},{value:"改造提升传统产业项目",label:"改造提升传统产业项目",children:[{value:"冶金",label:"冶金"},{value:"化工",label:"化工"},{value:"建材",label:"建材"},{value:"纺织",label:"纺织"},{value:"食品",label:"食品"},{value:"其他",label:"其他"}]}]}]},{value:"企业技术创新项目",label:"企业技术创新项目",children:[{value:"续建项目",label:"续建项目"},{value:"新开项目",label:"新开项目"}]},{value:"绿色制造项目",label:"绿色制造项目",children:[{value:"续建项目",label:"续建项目",children:[{value:"工业节能项目",label:"工业节能项目",children:""},{value:"工业节水项目",label:"工业节水项目",children:""},{value:"工业清洁生产项目",label:"工业清洁生产项目",children:""},{value:"资源循环利用项目",label:"资源循环利用项目",children:[{value:"资源综合利用项目",label:"资源综合利用项目"},{value:"再制造项目",label:"再制造项目"},{value:"秸秆工业原料化项目",label:"秸秆工业原料化项目"},{value:"新能源汽车动力蓄电池回收利用项目",label:"新能源汽车动力蓄电池回收利用项目"}]},{value:"节能环保产业项目",label:"节能环保产业项目",children:""}]},{value:"新开项目",label:"新开项目",children:[{value:"工业节能项目",label:"工业节能项目",children:""},{value:"工业节水项目",label:"工业节水项目",children:""},{value:"工业清洁生产项目",label:"工业清洁生产项目",children:""},{value:"资源循环利用项目",label:"资源循环利用项目",children:[{value:"资源综合利用项目",label:"资源综合利用项目"},{value:"再制造项目",label:"再制造项目"},{value:"秸秆工业原料化项目",label:"秸秆工业原料化项目"},{value:"新能源汽车动力蓄电池回收利用项目",label:"新能源汽车动力蓄电池回收利用项目"}]},{value:"节能环保产业项目",label:"节能环保产业项目",children:""}]}]},{value:"民营经济提升",label:"民营经济提升",children:[{value:"续建项目",label:"续建项目",children:[{value:"专精特新中小企业投资项目",label:"专精特新中小企业投资项目"},{value:"成长型小微企业项目",label:"成长型小微企业项目"},{value:"中小企业公共服务平台建设和服务项目",label:"中小企业公共服务平台建设和服务项目"},{value:"省级中小企业公共服务示范平台建设项目",label:"省级中小企业公共服务示范平台建设项目"},{value:"省级小微企业创业基地建设项目",label:"省级小微企业创业基地建设项目"}]},{value:"新开项目",label:"新开项目",children:[{value:"专精特新中小企业投资项目",label:"专精特新中小企业投资项目"},{value:"成长型小微企业项目",label:"成长型小微企业项目"},{value:"中小企业公共服务平台建设和服务项目",label:"中小企业公共服务平台建设和服务项目"},{value:"省级中小企业公共服务示范平台建设项目",label:"省级中小企业公共服务示范平台建设项目"},{value:"省级小微企业创业基地建设项目",label:"省级小微企业创业基地建设项目"}]}]},{value:"装备工业项目",label:"装备工业项目",children:[{value:"续建项目",label:"续建项目",children:[{value:"智能制造项目",label:"智能制造项目"},{value:"工业机器人应用项目",label:"工业机器人应用项目"},{value:"首台（套）重大技术装备研制项目",label:"首台（套）重大技术装备研制项目"}]},{value:"新开项目",label:"新开项目",children:[{value:"智能制造项目",label:"智能制造项目"},{value:"工业机器人应用项目",label:"工业机器人应用项目"},{value:"首台（套）重大技术装备研制项目",label:"首台（套）重大技术装备研制项目"}]}]},{value:"电子信息产业（电子信息制造业，软件业）",label:"电子信息产业（电子信息制造业，软件业）",children:[{value:"续建项目",label:"续建项目",children:[{value:"电子信息产品制造业",label:"电子信息产品制造业"},{value:"软件业",label:"软件业"},{value:"信息消费",label:"信息消费"}]},{value:"新开项目",label:"新开项目",children:[{value:"电子信息产品制造业",label:"电子信息产品制造业"},{value:"软件业",label:"软件业"},{value:"信息消费",label:"信息消费"}]}]},{value:"集成电路产业项目",label:"集成电路产业项目",children:[{value:"续建项目",label:"续建项目",children:[{value:"设计",label:"设计"},{value:"制造",label:"制造"},{value:"封装",label:"封装"},{value:"测试",label:"测试"},{value:"装备",label:"装备"},{value:"材料",label:"材料"},{value:"公共服务平台",label:"公共服务平台"}]},{value:"新开项目",label:"新开项目",children:[{value:"设计",label:"设计"},{value:"制造",label:"制造"},{value:"封装",label:"封装"},{value:"测试",label:"测试"},{value:"装备",label:"装备"},{value:"材料",label:"材料"},{value:"公共服务平台",label:"公共服务平台"}]}]},{value:"智能语音及人工智能产业发展项目",label:"智能语音及人工智能产业发展项目",children:[{value:"续建项目",label:"续建项目"},{value:"新开项目",label:"新开项目"}]},{value:"互联网与制造业融合项目",label:"互联网与制造业融合项目",children:[{value:"续建项目",label:"续建项目"},{value:"新开项目",label:"新开项目"}]},{value:"非煤矿山建设项目",label:"非煤矿山建设项目",children:[{value:"续建项目",label:"续建项目",children:[{value:"技术改造项目",label:"技术改造项目",children:[{value:"矿山安全项目",label:"矿山安全项目"},{value:"绿色发展项目",label:"绿色发展项目"},{value:"信息化建设项目",label:"信息化建设项目"}]}]},{value:"新开项目",label:"新开项目"}]},{value:"传统工艺美术保护发展项目",label:"传统工艺美术保护发展项目",children:[{value:"续建项目",label:"续建项目"},{value:"新开项目",label:"新开项目"}]}],form:{name:"",region:"",date1:"",date2:"",delivery:!1,type:[],resource:"",desc:"",city:""},value:"",options4:"",value1:"",value2:""},s()(e,"value3",""),s()(e,"apply",!0),s()(e,"val",""),e},props:["formData"],created:function(){this.baseForm.companyName=this.$store.state.firmName,this.formData&&(this.baseForm.companyName=this.formData.companyName,this.baseForm.projectName=this.formData.projectName,this.baseForm.projectContent=this.formData.projectContent,this.selectedOptions=[this.formData.province,this.formData.city,this.formData.district],this.indus=this.formData.projectType+"/"+this.formData.projectTypeDetail,this.city1=this.formData.province+"/"+this.formData.city+"/"+this.formData.district,this.total.totalInvestment=this.formData.totalInvestment,this.total.investmentSelfPrepare=this.formData.detailEntity.investmentSelfPrepare,this.total.investmentLoans=this.formData.detailEntity.investmentLoans,this.total.investmentUsingForex=this.formData.detailEntity.investmentUsingForex,this.expectedReturn.newSalesRevenue=this.formData.detailEntity.newSalesRevenue,this.expectedReturn.newProfits=this.formData.detailEntity.newProfits,this.expectedReturn.newTax=this.formData.detailEntity.newTax,this.expectedReturn.newForex=this.formData.detailEntity.newForex,this.socialResults.energySaving=this.formData.detailEntity.energySaving,this.socialResults.co2EmissionReduction=this.formData.detailEntity.co2EmissionReduction,this.socialResults.waterConservation=this.formData.detailEntity.waterConservation,this.socialResults.wasteUtilization=this.formData.detailEntity.wasteUtilization,this.socialResults.codEmissionReduction=this.formData.detailEntity.codEmissionReduction,this.socialResults.ammoniaNitrogenEmissionReduction=this.formData.detailEntity.ammoniaNitrogenEmissionReduction,this.socialResults.noxEmissionReduction=this.formData.detailEntity.noxEmissionReduction,this.socialResults.vocsEmissionReduction=this.formData.detailEntity.vocsEmissionReduction,this.socialResults.so2EmissionReduction=this.formData.detailEntity.so2EmissionReduction,this.socialResults.otherEmissionReduction=this.formData.detailEntity.otherEmissionReduction,this.remark=this.formData.detailEntity.remark)},methods:{toApply:function(){var e=this.o2Value;if(console.log(e),""==e)return this.$message({message:"请先选择申报类型",type:"warning"}),!1;this.apply=!1},onSubmit:function(){var e=this;if(console.log("submit!"),console.log(this.baseForm),""==this.baseForm.companyName)return this.$message({message:"请填写企业名称!",type:"warning"}),!1;if(""==this.baseForm.projectName)return this.$message({message:"请填写项目名称!",type:"warning"}),!1;if(""==this.baseForm.projectContent)return this.$message({message:"请填写项目内容!",type:"warning"}),!1;if(""==this.baseForm.projectType)return this.$message({message:"请选择项目类型!",type:"warning"}),!1;if(""==this.baseForm.projectTypeDetail)return this.$message({message:"请选择项目详情!",type:"warning"}),!1;if(""==this.baseForm.province)return this.$message({message:"请选择省份!",type:"warning"}),!1;if(""==this.baseForm.city)return this.$message({message:"请选择城市!",type:"warning"}),!1;if(""==this.baseForm.district)return this.$message({message:"请填写区县!",type:"warning"}),!1;this.baseForm.companyName,this.baseForm.projectName,this.baseForm.projectContent,this.baseForm.projectType,this.baseForm.projectTypeDetail,this.baseForm.province,this.baseForm.city,this.baseForm.district,this.advancedManufacturingType,this.total.totalInvestment,this.total.investmentSelfPrepare,this.total.investmentLoans,this.total.investmentUsingForex,this.expectedReturn.newSalesRevenue,this.expectedReturn.newProfits,this.expectedReturn.newTax,this.expectedReturn.newForex,this.socialResults.energySaving,this.socialResults.co2EmissionReduction,this.socialResults.waterConservation,this.socialResults.wasteUtilization,this.socialResults.codEmissionReduction,this.socialResults.ammoniaNitrogenEmissionReduction,this.socialResults.noxEmissionReduction,this.socialResults.vocsEmissionReduction,this.socialResults.so2EmissionReduction,this.socialResults.otherEmissionReduction,this.subjectYear.startDate.getFullYear(),this.subjectYear.endDate.getFullYear(),this.remark,this.$store.state.url;var t=this;this.$axios.post("industrialdeclaration/addDeclaration",this.$qs.stringify({declarationType:sessionStorage.getItem("declarationType"),isNew:sessionStorage.getItem("isNew"),declarationIndustry:sessionStorage.getItem("declarationIndustry"),industryTypeDetail:sessionStorage.getItem("industryTypeDetail"),companyName:this.baseForm.companyName,projectName:this.baseForm.projectName,projectContent:this.baseForm.projectContent,projectType:this.baseForm.projectType,projectTypeDetail:this.baseForm.projectTypeDetail,province:this.baseForm.province,city:this.baseForm.city,district:this.baseForm.district,"detailEntity.advancedManufacturingType":this.advancedManufacturingType,totalInvestment:this.total.totalInvestment,"detailEntity.investmentSelfPrepare":this.total.investmentSelfPrepare,"detailEntity.investmentLoans":this.total.investmentLoans,"detailEntity.investmentUsingForex":this.total.investmentUsingForex,"detailEntity.newSalesRevenue":this.expectedReturn.newSalesRevenue,"detailEntity.newProfits":this.expectedReturn.newProfits,"detailEntity.newTax":this.expectedReturn.newTax,"detailEntity.newForex":this.expectedReturn.newForex,"detailEntity.energySaving":this.socialResults.energySaving,"detailEntity.co2EmissionReduction":this.socialResults.co2EmissionReduction,"detailEntity.waterConservation":this.socialResults.waterConservation,"detailEntity.wasteUtilization":this.socialResults.wasteUtilization,"detailEntity.codEmissionReduction":this.socialResults.codEmissionReduction,"detailEntity.ammoniaNitrogenEmissionReduction":this.socialResults.ammoniaNitrogenEmissionReduction,"detailEntity.noxEmissionReduction":this.socialResults.noxEmissionReduction,"detailEntity.vocsEmissionReduction":this.socialResults.vocsEmissionReduction,"detailEntity.so2EmissionReduction":this.socialResults.so2EmissionReduction,"detailEntity.otherEmissionReduction":this.socialResults.otherEmissionReduction,startDate:this.subjectYear.startDate.getFullYear(),endDate:this.subjectYear.endDate.getFullYear(),"detailEntity.remark":this.remark})).then(function(e){console.log(e),0==e.data.code?(t.$message.success(e.data.msg),t.$router.push("/iHome/IndusChart")):t.$message({message:e.data.msg,type:"warning"})}).catch(function(t){e.$message.error("网络错误请稍后再试")})},handleChange1:function(){console.log(this.val),this.baseForm.projectType=this.val[0],this.baseForm.projectTypeDetail=this.val[1]},handleChange:function(e){var t=[];e.forEach(function(e,l){t.push(i.CodeToText[e])}),this.form.city=t,this.baseForm.province=t[0],this.baseForm.city=t[1],this.baseForm.district=t[2],console.log(this.form.city)},getClass2:function(){console.log(this.value),console.log(this.value1);var e=this.value-1;this.value1;console.log(this.orginData[e])},geto2Value:function(){console.log(this.o2Value)}}},n={render:function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticClass:"IndusForm",staticStyle:{width:"715px",margin:"0 auto"}},[e.formData?l("div",{staticClass:"applyForm"},[l("div",{staticClass:"applyForm-header",staticStyle:{"font-size":"20px","font-weight":"650","text-align":"center","margin-top":"41px"}},[e._v("\n      绿色制造项目申报表\n    ")]),e._v(" "),e._m(0),e._v(" "),l("el-form",{ref:"form",attrs:{model:e.form,"label-width":"80px"}},[l("el-form-item",{attrs:{label:"企业名称"}},[l("el-input",{attrs:{disabled:""},model:{value:e.baseForm.companyName,callback:function(t){e.$set(e.baseForm,"companyName",t)},expression:"baseForm.companyName"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"项目名称"}},[l("el-input",{model:{value:e.baseForm.projectName,callback:function(t){e.$set(e.baseForm,"projectName",t)},expression:"baseForm.projectName"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"建设内容"}},[l("el-input",{attrs:{type:"textarea"},model:{value:e.baseForm.projectContent,callback:function(t){e.$set(e.baseForm,"projectContent",t)},expression:"baseForm.projectContent"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"项目所属行业"}},[l("el-input",{model:{value:e.indus,callback:function(t){e.indus=t},expression:"indus"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"所在市县"}},[l("el-input",{model:{value:e.city1,callback:function(t){e.city1=t},expression:"city1"}}),e._v(" "),l("el-input",{model:{value:e.city1,callback:function(t){e.city1=t},expression:"city1"}})],1),e._v(" "),l("div",{staticStyle:{"font-size":"18px","font-weight":"650","margin-top":"33px","margin-bottom":"24px"}},[l("span",{staticStyle:{color:"#d04746","padding-top":"11px"}},[e._v("*")]),e._v("相关信息\n      ")]),e._v(" "),l("el-form-item",{attrs:{label:"* 高端制造业所属类别"}},[l("el-select",{attrs:{placeholder:"请选择"},model:{value:e.advancedManufacturingType,callback:function(t){e.advancedManufacturingType=t},expression:"advancedManufacturingType"}},e._l(e.option6,function(e){return l("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1),e._v(" "),l("el-form-item",{attrs:{label:"*总投资(万元)"}},[l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"总额"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.total.totalInvestment,callback:function(t){e.$set(e.total,"totalInvestment",t)},expression:"total.totalInvestment"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"自筹"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.total.investmentSelfPrepare,callback:function(t){e.$set(e.total,"investmentSelfPrepare",t)},expression:"total.investmentSelfPrepare"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"贷款"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.total.investmentLoans,callback:function(t){e.$set(e.total,"investmentLoans",t)},expression:"total.investmentLoans"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"其中用汇（万美元）"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.total.investmentUsingForex,callback:function(t){e.$set(e.total,"investmentUsingForex",t)},expression:"total.investmentUsingForex"}})],1)])],1)]),e._v(" "),l("el-form-item",{attrs:{label:"* 项目实施后预期效益（万元）"}},[l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"销售收入"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.expectedReturn.newSalesRevenue,callback:function(t){e.$set(e.expectedReturn,"newSalesRevenue",t)},expression:"expectedReturn.newSalesRevenue"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"利润"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.expectedReturn.newProfits,callback:function(t){e.$set(e.expectedReturn,"newProfits",t)},expression:"expectedReturn.newProfits"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"税金"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.expectedReturn.newTax,callback:function(t){e.$set(e.expectedReturn,"newTax",t)},expression:"expectedReturn.newTax"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"出口创汇（万美元）"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.expectedReturn.newForex,callback:function(t){e.$set(e.expectedReturn,"newForex",t)},expression:"expectedReturn.newForex"}})],1)])],1)]),e._v(" "),l("el-form-item",{attrs:{label:"* 社会效益(万元)"}},[l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"节能（吨标准煤/年）"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.energySaving,callback:function(t){e.$set(e.socialResults,"energySaving",t)},expression:"socialResults.energySaving"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"减排CO2(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.co2EmissionReduction,callback:function(t){e.$set(e.socialResults,"co2EmissionReduction",t)},expression:"socialResults.co2EmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"节水(万吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.waterConservation,callback:function(t){e.$set(e.socialResults,"waterConservation",t)},expression:"socialResults.waterConservation"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"废弃物利用(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.wasteUtilization,callback:function(t){e.$set(e.socialResults,"wasteUtilization",t)},expression:"socialResults.wasteUtilization"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"削减COD(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.codEmissionReduction,callback:function(t){e.$set(e.socialResults,"codEmissionReduction",t)},expression:"socialResults.codEmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"削减氨氮吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.ammoniaNitrogenEmissionReduction,callback:function(t){e.$set(e.socialResults,"ammoniaNitrogenEmissionReduction",t)},expression:"socialResults.ammoniaNitrogenEmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"削减NOX(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.noxEmissionReduction,callback:function(t){e.$set(e.socialResults,"noxEmissionReduction",t)},expression:"socialResults.noxEmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"削减VOCS(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.vocsEmissionReduction,callback:function(t){e.$set(e.socialResults,"vocsEmissionReduction",t)},expression:"socialResults.vocsEmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"削减SO2(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.so2EmissionReduction,callback:function(t){e.$set(e.socialResults,"so2EmissionReduction",t)},expression:"socialResults.so2EmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"其他"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.otherEmissionReduction,callback:function(t){e.$set(e.socialResults,"otherEmissionReduction",t)},expression:"socialResults.otherEmissionReduction"}})],1)])],1)]),e._v(" "),l("el-form-item",{attrs:{label:"* 项目起止年限"}},[l("el-date-picker",{attrs:{type:"year",placeholder:"选择年"},model:{value:e.subjectYear.startDate,callback:function(t){e.$set(e.subjectYear,"startDate",t)},expression:"subjectYear.startDate"}}),e._v(" "),l("span",[e._v("     至     ")]),e._v(" "),l("el-date-picker",{attrs:{type:"year",placeholder:"选择年"},model:{value:e.subjectYear.endDate,callback:function(t){e.$set(e.subjectYear,"endDate",t)},expression:"subjectYear.endDate"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"备注"}},[l("el-input",{attrs:{type:"textarea",autosize:{minRows:6}},model:{value:e.remark,callback:function(t){e.remark=t},expression:"remark"}})],1)],1)],1):e._e(),e._v(" "),e.formData?e._e():l("div",{staticClass:"applyForm"},[l("div",{staticClass:"applyForm-header",staticStyle:{"font-size":"20px","font-weight":"650","text-align":"center","margin-top":"41px"}},[e._v("\n      绿色制造项目申报表\n    ")]),e._v(" "),e._m(1),e._v(" "),l("el-form",{ref:"form",attrs:{model:e.form,"label-width":"80px"}},[l("el-form-item",{attrs:{label:"企业名称"}},[l("el-input",{attrs:{disabled:""},model:{value:e.baseForm.companyName,callback:function(t){e.$set(e.baseForm,"companyName",t)},expression:"baseForm.companyName"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"项目名称"}},[l("el-input",{model:{value:e.baseForm.projectName,callback:function(t){e.$set(e.baseForm,"projectName",t)},expression:"baseForm.projectName"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"建设内容"}},[l("el-input",{attrs:{type:"textarea"},model:{value:e.baseForm.projectContent,callback:function(t){e.$set(e.baseForm,"projectContent",t)},expression:"baseForm.projectContent"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"项目所属行业"}},[l("el-cascader",{attrs:{options:e.option},on:{change:e.handleChange1},model:{value:e.value,callback:function(t){e.value=t},expression:"value"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"所在市县"}},[l("el-cascader",{attrs:{size:"large",options:e.options},on:{change:e.handleChange},model:{value:e.selectedOptions,callback:function(t){e.selectedOptions=t},expression:"selectedOptions"}})],1),e._v(" "),l("div",{staticStyle:{"font-size":"18px","font-weight":"650","margin-top":"33px","margin-bottom":"24px"}},[l("span",{staticStyle:{color:"#d04746","padding-top":"11px"}},[e._v("*")]),e._v("相关信息\n      ")]),e._v(" "),l("el-form-item",{attrs:{label:"* 高端制造业所属类别"}},[l("el-select",{attrs:{placeholder:"请选择"},model:{value:e.advancedManufacturingType,callback:function(t){e.advancedManufacturingType=t},expression:"advancedManufacturingType"}},e._l(e.option6,function(e){return l("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1),e._v(" "),l("el-form-item",{attrs:{label:"*总投资(万元)"}},[l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"总额"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.total.totalInvestment,callback:function(t){e.$set(e.total,"totalInvestment",t)},expression:"total.totalInvestment"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"自筹"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.total.investmentSelfPrepare,callback:function(t){e.$set(e.total,"investmentSelfPrepare",t)},expression:"total.investmentSelfPrepare"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"贷款"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.total.investmentLoans,callback:function(t){e.$set(e.total,"investmentLoans",t)},expression:"total.investmentLoans"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"其中用汇（万美元）"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.total.investmentUsingForex,callback:function(t){e.$set(e.total,"investmentUsingForex",t)},expression:"total.investmentUsingForex"}})],1)])],1)]),e._v(" "),l("el-form-item",{attrs:{label:"* 项目实施后预期效益（万元）"}},[l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"销售收入"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.expectedReturn.newSalesRevenue,callback:function(t){e.$set(e.expectedReturn,"newSalesRevenue",t)},expression:"expectedReturn.newSalesRevenue"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"利润"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.expectedReturn.newProfits,callback:function(t){e.$set(e.expectedReturn,"newProfits",t)},expression:"expectedReturn.newProfits"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"税金"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.expectedReturn.newTax,callback:function(t){e.$set(e.expectedReturn,"newTax",t)},expression:"expectedReturn.newTax"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"出口创汇（万美元）"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.expectedReturn.newForex,callback:function(t){e.$set(e.expectedReturn,"newForex",t)},expression:"expectedReturn.newForex"}})],1)])],1)]),e._v(" "),l("el-form-item",{attrs:{label:"* 社会效益(万元)"}},[l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"节能（吨标准煤/年）"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.energySaving,callback:function(t){e.$set(e.socialResults,"energySaving",t)},expression:"socialResults.energySaving"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"减排CO2(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.co2EmissionReduction,callback:function(t){e.$set(e.socialResults,"co2EmissionReduction",t)},expression:"socialResults.co2EmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"节水(万吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.waterConservation,callback:function(t){e.$set(e.socialResults,"waterConservation",t)},expression:"socialResults.waterConservation"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"废弃物利用(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.wasteUtilization,callback:function(t){e.$set(e.socialResults,"wasteUtilization",t)},expression:"socialResults.wasteUtilization"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"削减COD(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.codEmissionReduction,callback:function(t){e.$set(e.socialResults,"codEmissionReduction",t)},expression:"socialResults.codEmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"削减氨氮吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.ammoniaNitrogenEmissionReduction,callback:function(t){e.$set(e.socialResults,"ammoniaNitrogenEmissionReduction",t)},expression:"socialResults.ammoniaNitrogenEmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"削减NOX(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.noxEmissionReduction,callback:function(t){e.$set(e.socialResults,"noxEmissionReduction",t)},expression:"socialResults.noxEmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"削减VOCS(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.vocsEmissionReduction,callback:function(t){e.$set(e.socialResults,"vocsEmissionReduction",t)},expression:"socialResults.vocsEmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"削减SO2(吨/年)"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.so2EmissionReduction,callback:function(t){e.$set(e.socialResults,"so2EmissionReduction",t)},expression:"socialResults.so2EmissionReduction"}})],1)])],1),e._v(" "),l("div",{staticStyle:{"margin-bottom":"10px"}},[l("el-form-item",{attrs:{label:"其他"}},[l("div",{staticClass:"class2"},[l("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.socialResults.otherEmissionReduction,callback:function(t){e.$set(e.socialResults,"otherEmissionReduction",t)},expression:"socialResults.otherEmissionReduction"}})],1)])],1)]),e._v(" "),l("el-form-item",{attrs:{label:"* 项目起止年限"}},[l("el-date-picker",{attrs:{type:"year",placeholder:"选择年"},model:{value:e.subjectYear.startDate,callback:function(t){e.$set(e.subjectYear,"startDate",t)},expression:"subjectYear.startDate"}}),e._v(" "),l("span",[e._v("     至     ")]),e._v(" "),l("el-date-picker",{attrs:{type:"year",placeholder:"选择年"},model:{value:e.subjectYear.endDate,callback:function(t){e.$set(e.subjectYear,"endDate",t)},expression:"subjectYear.endDate"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"备注"}},[l("el-input",{attrs:{type:"textarea",autosize:{minRows:6}},model:{value:e.remark,callback:function(t){e.remark=t},expression:"remark"}})],1),e._v(" "),l("el-form-item",[l("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("立即创建")]),e._v(" "),l("el-button",{on:{click:function(t){return e.$router.push("/iHome/IndusForm")}}},[e._v("取消")])],1)],1)],1)])},staticRenderFns:[function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticStyle:{"font-size":"18px","font-weight":"650","margin-top":"33px","margin-bottom":"24px"}},[t("span",{staticStyle:{color:"#d04746","padding-top":"11px"}},[this._v("*")]),this._v("基本信息\n    ")])},function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticStyle:{"font-size":"18px","font-weight":"650","margin-top":"33px","margin-bottom":"24px"}},[t("span",{staticStyle:{color:"#d04746","padding-top":"11px"}},[this._v("*")]),this._v("基本信息\n    ")])}]};var r=l("VU/8")(o,n,!1,function(e){l("HgB9")},null,null);t.default=r.exports},HgB9:function(e,t){}});
//# sourceMappingURL=27.f2f0dc9a9a54d1fde210.js.map