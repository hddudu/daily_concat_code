package com.homework.gupao.designpattern.prototype.chonggou;

import java.util.Date;
/**
 * 暂无重构
 *  申请用户
 * @author dudu
 *
 */
public class BidUser {

	private Long id;
	
	private String enterpriesName;//企业名称 
	
	private String orgID;//组织机构代码/三证合一号
	
	private String enterpriesAddress;
	
	private Long agentId;
	
	private String agentEmail;
	
	private String agentFox;
	
	private String agentIdentifyNum;
	
	private String agentName;
	
	private String agentTelphone;
	
	private String agentFixedTelphone;
	
	private String bankAccountName;
	
	private String bankAccountNo;
	
	private String bankName;
	
	private String bankLocation;
	
	private String subBrachBankName;
	
	private String subBrachBankId;
	
	private Integer userIdType;
	
	private String userIdNum;
	
	private String registerAddress;
	
	private String registerDate; // datetime
	
	private String controllerIdNum;
	
	private String controllerWorkId;
	
	private String controllerEmail;
	
	private Integer controllerMarry;
	
	private String controllerName;
	
	private String controllerPhone;
	
	private Integer controllerWorkYear;
	
	private String bossJob;
	
	private String bossEmail;
	
	private String bossIdNum;
	
	private String bossName;
	
	private String bossNation;
	
	private String bossPhone;
	
	private Integer enterpriseType;
	
	private String enterpriseTypeName;
	
	private String expressAddress;
	
	private String expressPerson;
	
	private String expressPhone;
	
	private String drawerName;
	
	private String drawerPhone;
	
	private Integer driverCount;
	
	private Integer staffCount;
	
	private String serivceIndustry;
	
	private Double averageMoney;
	
	private Double averageMoneyGK;
	
	private Double averageMoneyZY;
	
	private Double averageFuelMoney;
	
	private Integer totalChannelCount;
	
	private String operationMode;
	
	private String operationArae;
	
	private Integer vehicleZY;
	
	private Integer vehicleGK;
	
	private String financialReportTime;
	
	private Double monetaryFund;
	
	private Double tradeFund;
	
	private Double receiveDebt;
	
	private Double fixedAssets;
	
	private String depreciation;
	
	private Double fixedAssetsClear;
	
	private Double longTermCosts;
	
	private Double totalAssets;
	
	private Double shortTermLoan;
	
	private Double payableLoan;
	
	private Double advancePayLoan;
	
	private Double payableRemunerate;
	
	private Double payableTax;
	
	private Double longTermLoan;
	
	private Double income;
	
	private Double mainIncome;
	
	private Double operationCost;
	
	private Double sellCost;
	
	private Double managerCost;
	
	private Double assetsLoss;
	
	private Double investmentIncome;
	
	private Double totalProfit;
	
	private Integer serivceTime;
	
	private String listCard;
	
	private Date createTime;
	
	private Long creditLimit ; //授信额度
	
	private Long limitStartTime;//额度起期yyyymmdd
	
	private Long limitEndTime;//额度止期yyyymmdd
	
	private String creditStatus;//授信状态 (成功/失败)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnterpriesName() {
		return enterpriesName;
	}

	public void setEnterpriesName(String enterpriesName) {
		this.enterpriesName = enterpriesName;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getEnterpriesAddress() {
		return enterpriesAddress;
	}

	public void setEnterpriesAddress(String enterpriesAddress) {
		this.enterpriesAddress = enterpriesAddress;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getAgentEmail() {
		return agentEmail;
	}

	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}

	public String getAgentFox() {
		return agentFox;
	}

	public void setAgentFox(String agentFox) {
		this.agentFox = agentFox;
	}

	public String getAgentIdentifyNum() {
		return agentIdentifyNum;
	}

	public void setAgentIdentifyNum(String agentIdentifyNum) {
		this.agentIdentifyNum = agentIdentifyNum;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentTelphone() {
		return agentTelphone;
	}

	public void setAgentTelphone(String agentTelphone) {
		this.agentTelphone = agentTelphone;
	}

	public String getAgentFixedTelphone() {
		return agentFixedTelphone;
	}

	public void setAgentFixedTelphone(String agentFixedTelphone) {
		this.agentFixedTelphone = agentFixedTelphone;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankLocation() {
		return bankLocation;
	}

	public void setBankLocation(String bankLocation) {
		this.bankLocation = bankLocation;
	}

	public String getSubBrachBankName() {
		return subBrachBankName;
	}

	public void setSubBrachBankName(String subBrachBankName) {
		this.subBrachBankName = subBrachBankName;
	}

	public String getSubBrachBankId() {
		return subBrachBankId;
	}

	public void setSubBrachBankId(String subBrachBankId) {
		this.subBrachBankId = subBrachBankId;
	}

	public Integer getUserIdType() {
		return userIdType;
	}

	public void setUserIdType(Integer userIdType) {
		this.userIdType = userIdType;
	}

	public String getUserIdNum() {
		return userIdNum;
	}

	public void setUserIdNum(String userIdNum) {
		this.userIdNum = userIdNum;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getControllerIdNum() {
		return controllerIdNum;
	}

	public void setControllerIdNum(String controllerIdNum) {
		this.controllerIdNum = controllerIdNum;
	}

	public String getControllerWorkId() {
		return controllerWorkId;
	}

	public void setControllerWorkId(String controllerWorkId) {
		this.controllerWorkId = controllerWorkId;
	}

	public String getControllerEmail() {
		return controllerEmail;
	}

	public void setControllerEmail(String controllerEmail) {
		this.controllerEmail = controllerEmail;
	}

	public Integer getControllerMarry() {
		return controllerMarry;
	}

	public void setControllerMarry(Integer controllerMarry) {
		this.controllerMarry = controllerMarry;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getControllerPhone() {
		return controllerPhone;
	}

	public void setControllerPhone(String controllerPhone) {
		this.controllerPhone = controllerPhone;
	}

	public Integer getControllerWorkYear() {
		return controllerWorkYear;
	}

	public void setControllerWorkYear(Integer controllerWorkYear) {
		this.controllerWorkYear = controllerWorkYear;
	}

	public String getBossJob() {
		return bossJob;
	}

	public void setBossJob(String bossJob) {
		this.bossJob = bossJob;
	}

	public String getBossEmail() {
		return bossEmail;
	}

	public void setBossEmail(String bossEmail) {
		this.bossEmail = bossEmail;
	}

	public String getBossIdNum() {
		return bossIdNum;
	}

	public void setBossIdNum(String bossIdNum) {
		this.bossIdNum = bossIdNum;
	}

	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public String getBossNation() {
		return bossNation;
	}

	public void setBossNation(String bossNation) {
		this.bossNation = bossNation;
	}

	public String getBossPhone() {
		return bossPhone;
	}

	public void setBossPhone(String bossPhone) {
		this.bossPhone = bossPhone;
	}

	public Integer getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(Integer enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getEnterpriseTypeName() {
		return enterpriseTypeName;
	}

	public void setEnterpriseTypeName(String enterpriseTypeName) {
		this.enterpriseTypeName = enterpriseTypeName;
	}

	public String getExpressAddress() {
		return expressAddress;
	}

	public void setExpressAddress(String expressAddress) {
		this.expressAddress = expressAddress;
	}

	public String getExpressPerson() {
		return expressPerson;
	}

	public void setExpressPerson(String expressPerson) {
		this.expressPerson = expressPerson;
	}

	public String getExpressPhone() {
		return expressPhone;
	}

	public void setExpressPhone(String expressPhone) {
		this.expressPhone = expressPhone;
	}

	public String getDrawerName() {
		return drawerName;
	}

	public void setDrawerName(String drawerName) {
		this.drawerName = drawerName;
	}

	public String getDrawerPhone() {
		return drawerPhone;
	}

	public void setDrawerPhone(String drawerPhone) {
		this.drawerPhone = drawerPhone;
	}

	public Integer getDriverCount() {
		return driverCount;
	}

	public void setDriverCount(Integer driverCount) {
		this.driverCount = driverCount;
	}

	public Integer getStaffCount() {
		return staffCount;
	}

	public void setStaffCount(Integer staffCount) {
		this.staffCount = staffCount;
	}

	public String getSerivceIndustry() {
		return serivceIndustry;
	}

	public void setSerivceIndustry(String serivceIndustry) {
		this.serivceIndustry = serivceIndustry;
	}

	public Double getAverageMoney() {
		return averageMoney;
	}

	public void setAverageMoney(Double averageMoney) {
		this.averageMoney = averageMoney;
	}

	public Double getAverageMoneyGK() {
		return averageMoneyGK;
	}

	public void setAverageMoneyGK(Double averageMoneyGK) {
		this.averageMoneyGK = averageMoneyGK;
	}

	public Double getAverageMoneyZY() {
		return averageMoneyZY;
	}

	public void setAverageMoneyZY(Double averageMoneyZY) {
		this.averageMoneyZY = averageMoneyZY;
	}

	public Double getAverageFuelMoney() {
		return averageFuelMoney;
	}

	public void setAverageFuelMoney(Double averageFuelMoney) {
		this.averageFuelMoney = averageFuelMoney;
	}

	public Integer getTotalChannelCount() {
		return totalChannelCount;
	}

	public void setTotalChannelCount(Integer totalChannelCount) {
		this.totalChannelCount = totalChannelCount;
	}

	public String getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(String operationMode) {
		this.operationMode = operationMode;
	}

	public String getOperationArae() {
		return operationArae;
	}

	public void setOperationArae(String operationArae) {
		this.operationArae = operationArae;
	}

	public Integer getVehicleZY() {
		return vehicleZY;
	}

	public void setVehicleZY(Integer vehicleZY) {
		this.vehicleZY = vehicleZY;
	}

	public Integer getVehicleGK() {
		return vehicleGK;
	}

	public void setVehicleGK(Integer vehicleGK) {
		this.vehicleGK = vehicleGK;
	}

	public String getFinancialReportTime() {
		return financialReportTime;
	}

	public void setFinancialReportTime(String financialReportTime) {
		this.financialReportTime = financialReportTime;
	}

	public Double getMonetaryFund() {
		return monetaryFund;
	}

	public void setMonetaryFund(Double monetaryFund) {
		this.monetaryFund = monetaryFund;
	}

	public Double getTradeFund() {
		return tradeFund;
	}

	public void setTradeFund(Double tradeFund) {
		this.tradeFund = tradeFund;
	}

	public Double getReceiveDebt() {
		return receiveDebt;
	}

	public void setReceiveDebt(Double receiveDebt) {
		this.receiveDebt = receiveDebt;
	}

	public Double getFixedAssets() {
		return fixedAssets;
	}

	public void setFixedAssets(Double fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public String getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}

	public Double getFixedAssetsClear() {
		return fixedAssetsClear;
	}

	public void setFixedAssetsClear(Double fixedAssetsClear) {
		this.fixedAssetsClear = fixedAssetsClear;
	}

	public Double getLongTermCosts() {
		return longTermCosts;
	}

	public void setLongTermCosts(Double longTermCosts) {
		this.longTermCosts = longTermCosts;
	}

	public Double getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
	}

	public Double getShortTermLoan() {
		return shortTermLoan;
	}

	public void setShortTermLoan(Double shortTermLoan) {
		this.shortTermLoan = shortTermLoan;
	}

	public Double getPayableLoan() {
		return payableLoan;
	}

	public void setPayableLoan(Double payableLoan) {
		this.payableLoan = payableLoan;
	}

	public Double getAdvancePayLoan() {
		return advancePayLoan;
	}

	public void setAdvancePayLoan(Double advancePayLoan) {
		this.advancePayLoan = advancePayLoan;
	}

	public Double getPayableRemunerate() {
		return payableRemunerate;
	}

	public void setPayableRemunerate(Double payableRemunerate) {
		this.payableRemunerate = payableRemunerate;
	}

	public Double getPayableTax() {
		return payableTax;
	}

	public void setPayableTax(Double payableTax) {
		this.payableTax = payableTax;
	}

	public Double getLongTermLoan() {
		return longTermLoan;
	}

	public void setLongTermLoan(Double longTermLoan) {
		this.longTermLoan = longTermLoan;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getMainIncome() {
		return mainIncome;
	}

	public void setMainIncome(Double mainIncome) {
		this.mainIncome = mainIncome;
	}

	public Double getOperationCost() {
		return operationCost;
	}

	public void setOperationCost(Double operationCost) {
		this.operationCost = operationCost;
	}

	public Double getSellCost() {
		return sellCost;
	}

	public void setSellCost(Double sellCost) {
		this.sellCost = sellCost;
	}

	public Double getManagerCost() {
		return managerCost;
	}

	public void setManagerCost(Double managerCost) {
		this.managerCost = managerCost;
	}

	public Double getAssetsLoss() {
		return assetsLoss;
	}

	public void setAssetsLoss(Double assetsLoss) {
		this.assetsLoss = assetsLoss;
	}

	public Double getInvestmentIncome() {
		return investmentIncome;
	}

	public void setInvestmentIncome(Double investmentIncome) {
		this.investmentIncome = investmentIncome;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Integer getSerivceTime() {
		return serivceTime;
	}

	public void setSerivceTime(Integer serivceTime) {
		this.serivceTime = serivceTime;
	}

	public String getListCard() {
		return listCard;
	}

	public void setListCard(String listCard) {
		this.listCard = listCard;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Long creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Long getLimitStartTime() {
		return limitStartTime;
	}

	public void setLimitStartTime(Long limitStartTime) {
		this.limitStartTime = limitStartTime;
	}

	public Long getLimitEndTime() {
		return limitEndTime;
	}

	public void setLimitEndTime(Long limitEndTime) {
		this.limitEndTime = limitEndTime;
	}

	public String getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}
	
	
}
