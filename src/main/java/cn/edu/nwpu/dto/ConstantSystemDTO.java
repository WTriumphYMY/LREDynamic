package cn.edu.nwpu.dto;

/**
 * @ClassName ConstantSystemDTO
 * @Author: wkx
 * @Date: 2019/7/18 19:26
 * @Version: v1.0
 * @Description: 恒压双组元挤压式系统数据传输类
 */
public class ConstantSystemDTO {
    private String bottleVol;
    private String bottleCA;
    private String bottlePressure;
    private String bottleTemperature;
    private String bottleGas;
    private String reduceC;
    private String reduceK;
    private String reduceF;
    private String reduceM;
    private String reduceAmb;
    private String reduceAvc;
    private String reduceV1;
    private String reduceV2;
    private String reduceXstop;
    private String fuelTankCA;
    private String fuelTankV0;
    private String fuelTankPressure;
    private String fuelTankFuel;
    private String oxidTankCA;
    private String oxidTankV0;
    private String oxidTankPressure;
    private String oxidTankFuel;
    private String fuelSolenoidR;
    private String fuelSolenoidN;
    private String fuelSolenoidU;
    private String fuelSolenoidSigma;
    private String fuelSolenoidSm;
    private String fuelSolenoidK;
    private String fuelSolenoidM;
    private String fuelSolenoidF;
    private String fuelSolenoidD;
    private String fuelSolenoidXstop;
    private String oxidSolenoidR;
    private String oxidSolenoidN;
    private String oxidSolenoidU;
    private String oxidSolenoidSigma;
    private String oxidSolenoidSm;
    private String oxidSolenoidK;
    private String oxidSolenoidM;
    private String oxidSolenoidF;
    private String oxidSolenoidD;
    private String oxidSolenoidXstop;
    private String fuelLiquidOrificeD;
    private String fuelLiquidOrificePc;
    private String oxidLiquidOrificeD;
    private String oxidLiquidOrificePc;
    private String thrustChamberV;
    private String thrustChamberTauc;
    private String thrustChamberK;
    private String thrustChamberD;
    private String thrustChamberPa;
    private String thrustChamberEps;
    private String globalParasTime;
    private String globalParasStep;

    //测试组件用的压强、流量、温度入口与压强出口,推进剂流量入口
    private String pin;
    private String qin;
    private String tin;
    private String pe;
    private String qo;
    private String qf;

    public String getBottleVol() {
        return bottleVol;
    }

    public void setBottleVol(String bottleVol) {
        this.bottleVol = bottleVol;
    }

    public String getBottleCA() {
        return bottleCA;
    }

    public void setBottleCA(String bottleCA) {
        this.bottleCA = bottleCA;
    }

    public String getBottlePressure() {
        return bottlePressure;
    }

    public void setBottlePressure(String bottlePressure) {
        this.bottlePressure = bottlePressure;
    }

    public String getBottleTemperature() {
        return bottleTemperature;
    }

    public void setBottleTemperature(String bottleTemperature) {
        this.bottleTemperature = bottleTemperature;
    }

    public String getBottleGas() {
        return bottleGas;
    }

    public void setBottleGas(String bottleGas) {
        this.bottleGas = bottleGas;
    }

    public String getReduceC() {
        return reduceC;
    }

    public void setReduceC(String reduceC) {
        this.reduceC = reduceC;
    }

    public String getReduceK() {
        return reduceK;
    }

    public void setReduceK(String reduceK) {
        this.reduceK = reduceK;
    }

    public String getReduceF() {
        return reduceF;
    }

    public void setReduceF(String reduceF) {
        this.reduceF = reduceF;
    }

    public String getReduceM() {
        return reduceM;
    }

    public void setReduceM(String reduceM) {
        this.reduceM = reduceM;
    }

    public String getReduceAmb() {
        return reduceAmb;
    }

    public void setReduceAmb(String reduceAmb) {
        this.reduceAmb = reduceAmb;
    }

    public String getReduceAvc() {
        return reduceAvc;
    }

    public void setReduceAvc(String reduceAvc) {
        this.reduceAvc = reduceAvc;
    }

    public String getReduceV1() {
        return reduceV1;
    }

    public void setReduceV1(String reduceV1) {
        this.reduceV1 = reduceV1;
    }

    public String getReduceV2() {
        return reduceV2;
    }

    public void setReduceV2(String reduceV2) {
        this.reduceV2 = reduceV2;
    }

    public String getReduceXstop() {
        return reduceXstop;
    }

    public void setReduceXstop(String reduceXstop) {
        this.reduceXstop = reduceXstop;
    }

    public String getFuelTankCA() {
        return fuelTankCA;
    }

    public void setFuelTankCA(String fuelTankCA) {
        this.fuelTankCA = fuelTankCA;
    }

    public String getFuelTankV0() {
        return fuelTankV0;
    }

    public void setFuelTankV0(String fuelTankV0) {
        this.fuelTankV0 = fuelTankV0;
    }

    public String getFuelTankPressure() {
        return fuelTankPressure;
    }

    public void setFuelTankPressure(String fuelTankPressure) {
        this.fuelTankPressure = fuelTankPressure;
    }

    public String getFuelTankFuel() {
        return fuelTankFuel;
    }

    public void setFuelTankFuel(String fuelTankFuel) {
        this.fuelTankFuel = fuelTankFuel;
    }

    public String getOxidTankCA() {
        return oxidTankCA;
    }

    public void setOxidTankCA(String oxidTankCA) {
        this.oxidTankCA = oxidTankCA;
    }

    public String getOxidTankV0() {
        return oxidTankV0;
    }

    public void setOxidTankV0(String oxidTankV0) {
        this.oxidTankV0 = oxidTankV0;
    }

    public String getOxidTankPressure() {
        return oxidTankPressure;
    }

    public void setOxidTankPressure(String oxidTankPressure) {
        this.oxidTankPressure = oxidTankPressure;
    }

    public String getOxidTankFuel() {
        return oxidTankFuel;
    }

    public void setOxidTankFuel(String oxidTankFuel) {
        this.oxidTankFuel = oxidTankFuel;
    }

    public String getFuelSolenoidR() {
        return fuelSolenoidR;
    }

    public void setFuelSolenoidR(String fuelSolenoidR) {
        this.fuelSolenoidR = fuelSolenoidR;
    }

    public String getFuelSolenoidN() {
        return fuelSolenoidN;
    }

    public void setFuelSolenoidN(String fuelSolenoidN) {
        this.fuelSolenoidN = fuelSolenoidN;
    }

    public String getFuelSolenoidU() {
        return fuelSolenoidU;
    }

    public void setFuelSolenoidU(String fuelSolenoidU) {
        this.fuelSolenoidU = fuelSolenoidU;
    }

    public String getFuelSolenoidSigma() {
        return fuelSolenoidSigma;
    }

    public void setFuelSolenoidSigma(String fuelSolenoidSigma) {
        this.fuelSolenoidSigma = fuelSolenoidSigma;
    }

    public String getFuelSolenoidSm() {
        return fuelSolenoidSm;
    }

    public void setFuelSolenoidSm(String fuelSolenoidSm) {
        this.fuelSolenoidSm = fuelSolenoidSm;
    }

    public String getFuelSolenoidK() {
        return fuelSolenoidK;
    }

    public void setFuelSolenoidK(String fuelSolenoidK) {
        this.fuelSolenoidK = fuelSolenoidK;
    }

    public String getFuelSolenoidM() {
        return fuelSolenoidM;
    }

    public void setFuelSolenoidM(String fuelSolenoidM) {
        this.fuelSolenoidM = fuelSolenoidM;
    }

    public String getFuelSolenoidF() {
        return fuelSolenoidF;
    }

    public void setFuelSolenoidF(String fuelSolenoidF) {
        this.fuelSolenoidF = fuelSolenoidF;
    }

    public String getFuelSolenoidD() {
        return fuelSolenoidD;
    }

    public void setFuelSolenoidD(String fuelSolenoidD) {
        this.fuelSolenoidD = fuelSolenoidD;
    }

    public String getFuelSolenoidXstop() {
        return fuelSolenoidXstop;
    }

    public void setFuelSolenoidXstop(String fuelSolenoidXstop) {
        this.fuelSolenoidXstop = fuelSolenoidXstop;
    }

    public String getOxidSolenoidR() {
        return oxidSolenoidR;
    }

    public void setOxidSolenoidR(String oxidSolenoidR) {
        this.oxidSolenoidR = oxidSolenoidR;
    }

    public String getOxidSolenoidN() {
        return oxidSolenoidN;
    }

    public void setOxidSolenoidN(String oxidSolenoidN) {
        this.oxidSolenoidN = oxidSolenoidN;
    }

    public String getOxidSolenoidU() {
        return oxidSolenoidU;
    }

    public void setOxidSolenoidU(String oxidSolenoidU) {
        this.oxidSolenoidU = oxidSolenoidU;
    }

    public String getOxidSolenoidSigma() {
        return oxidSolenoidSigma;
    }

    public void setOxidSolenoidSigma(String oxidSolenoidSigma) {
        this.oxidSolenoidSigma = oxidSolenoidSigma;
    }

    public String getOxidSolenoidSm() {
        return oxidSolenoidSm;
    }

    public void setOxidSolenoidSm(String oxidSolenoidSm) {
        this.oxidSolenoidSm = oxidSolenoidSm;
    }

    public String getOxidSolenoidK() {
        return oxidSolenoidK;
    }

    public void setOxidSolenoidK(String oxidSolenoidK) {
        this.oxidSolenoidK = oxidSolenoidK;
    }

    public String getOxidSolenoidM() {
        return oxidSolenoidM;
    }

    public void setOxidSolenoidM(String oxidSolenoidM) {
        this.oxidSolenoidM = oxidSolenoidM;
    }

    public String getOxidSolenoidF() {
        return oxidSolenoidF;
    }

    public void setOxidSolenoidF(String oxidSolenoidF) {
        this.oxidSolenoidF = oxidSolenoidF;
    }

    public String getOxidSolenoidD() {
        return oxidSolenoidD;
    }

    public void setOxidSolenoidD(String oxidSolenoidD) {
        this.oxidSolenoidD = oxidSolenoidD;
    }

    public String getOxidSolenoidXstop() {
        return oxidSolenoidXstop;
    }

    public void setOxidSolenoidXstop(String oxidSolenoidXstop) {
        this.oxidSolenoidXstop = oxidSolenoidXstop;
    }

    public String getFuelLiquidOrificeD() {
        return fuelLiquidOrificeD;
    }

    public void setFuelLiquidOrificeD(String fuelLiquidOrificeD) {
        this.fuelLiquidOrificeD = fuelLiquidOrificeD;
    }

    public String getFuelLiquidOrificePc() {
        return fuelLiquidOrificePc;
    }

    public void setFuelLiquidOrificePc(String fuelLiquidOrificePc) {
        this.fuelLiquidOrificePc = fuelLiquidOrificePc;
    }

    public String getOxidLiquidOrificeD() {
        return oxidLiquidOrificeD;
    }

    public void setOxidLiquidOrificeD(String oxidLiquidOrificeD) {
        this.oxidLiquidOrificeD = oxidLiquidOrificeD;
    }

    public String getOxidLiquidOrificePc() {
        return oxidLiquidOrificePc;
    }

    public void setOxidLiquidOrificePc(String oxidLiquidOrificePc) {
        this.oxidLiquidOrificePc = oxidLiquidOrificePc;
    }

    public String getThrustChamberV() {
        return thrustChamberV;
    }

    public void setThrustChamberV(String thrustChamberV) {
        this.thrustChamberV = thrustChamberV;
    }

    public String getThrustChamberTauc() {
        return thrustChamberTauc;
    }

    public void setThrustChamberTauc(String thrustChamberTauc) {
        this.thrustChamberTauc = thrustChamberTauc;
    }

    public String getThrustChamberK() {
        return thrustChamberK;
    }

    public void setThrustChamberK(String thrustChamberK) {
        this.thrustChamberK = thrustChamberK;
    }

    public String getThrustChamberD() {
        return thrustChamberD;
    }

    public void setThrustChamberD(String thrustChamberD) {
        this.thrustChamberD = thrustChamberD;
    }

    public String getThrustChamberPa() {
        return thrustChamberPa;
    }

    public void setThrustChamberPa(String thrustChamberPa) {
        this.thrustChamberPa = thrustChamberPa;
    }

    public String getThrustChamberEps() {
        return thrustChamberEps;
    }

    public void setThrustChamberEps(String thrustChamberEps) {
        this.thrustChamberEps = thrustChamberEps;
    }

    public String getGlobalParasTime() {
        return globalParasTime;
    }

    public void setGlobalParasTime(String globalParasTime) {
        this.globalParasTime = globalParasTime;
    }

    public String getGlobalParasStep() {
        return globalParasStep;
    }

    public void setGlobalParasStep(String globalParasStep) {
        this.globalParasStep = globalParasStep;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getQin() {
        return qin;
    }

    public void setQin(String qin) {
        this.qin = qin;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getQo() {
        return qo;
    }

    public void setQo(String qo) {
        this.qo = qo;
    }

    public String getQf() {
        return qf;
    }

    public void setQf(String qf) {
        this.qf = qf;
    }
}
