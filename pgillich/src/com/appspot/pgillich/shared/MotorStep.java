package com.appspot.pgillich.shared;

import java.io.Serializable;

public class MotorStep implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int FIELD_NUM = 13;
	//fok,p,V,T-Tabs0,m,U,szA[0],szA[1],s,dHTR,dmdm[2],dm_eg*Egesho/dfok
	public double fok;
	public double p;
	public double V;
	public double T_Tabs0;
	public double m;
	public double U;
	public double szA_0;
	public double szA_1;
	public double s;
	public double dHTR;
	public double dmdm_2;
	public double dE_eges_dfok;
	public double p_bar;
}
