package com.appspot.pgillich.engineering.motor46;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.System;

import com.appspot.pgillich.shared.MotorStep;

public class Motor46 
{
	
//	#define TRUE 1
//	#define FALSE 0
//	#define AND &&
//	#define OR ||
//	#define RAD 57.2957795130823
//	#define Re 8.314
//	#define Tabs0 273.15
//	#define pnorm 101325.0
//	#define KOMPONENSEK 5
//	#define OXIGEN 1

	final double RAD = Math.toDegrees(1.0);
	final double Re = 8.314472;
	final double Tabs0 = 273.15;
	final double pnorm = 101325.0;
	final int KOMPONENSEK = 5;
	final int OXIGEN = 1;
	
//	#define SZIVO 0
//	#define KIPUFOGO 1
//	#define GYURUHORONY 2
//	#define SZELEPEK 3

	final int SZIVO = 0;
	final int KIPUFOGO = 1;
	final int GYURUHORONY = 2;
	final int SZELEPEK = 3;

//	/*typedef double valos;*/
//	typedef valos komponens[KOMPONENSEK];
//	typedef FILE * file;
	
//	/*						  		N//2  	O//2  	 Ar	  CO//2		H//2O	*/
//	komponens	q0i=	{		0.7565, 	0.2029, 	 0.009,	0.0003,	0.0313};	/* A gázalkotók kezdeti tömegarányai */
//	komponens	kappa0i={	1.40,		1.40,		 1.63,	1.30,		 1.40};	/* A gázalkotók adiabatikus kitevői */
//	komponens	M0i=	{ .0280134, .0319988, .039948, .04400995, .01801534};	/* A gázalkotók moltömegei */
//	komponens	Rsi,		/* A gázalkotók specifikus gázállandói */
//					cvi,cpi,	/* A gázalkotók fajhői */
//					qi,		/* A gázalkotók tömegarányai */
//					mi,		/* A gázalkotók tömegei */
//					dmi,		/* A gázalkotók tömegváltozásai dt idő alatt */
//					Mi;		/* A gázalkotók moltömege */
	
	double[] q0i= {		0.7565, 	0.2029, 	 0.009,	0.0003,	0.0313};
	double[] kappa0i={	1.40,		1.40,		 1.63,	1.30,		 1.40};
	double[] M0i=	{ .0280134, .0319988, .039948, .04400995, .01801534};
	double[] Rsi = new double[5],
				cvi = new double[5], cpi = new double[5],
				qi = new double[5],		
				mi = new double[5],		
				dmi = new double[5],	
				Mi = new double[5];	
	
//	/* Gázjellemzők */
//	valos	cp,cv,	/* A gázkeverék fajhői */
//			kappa,	/* A gázkeverék adiabatikus kitevője */
//			m,			/* A gáz tömege */
//			V,       /* A gáz térfogata */
//			v,			/* A gáz fajtéfogata */
//			U,			/* A gáz belső energiája */
//			Rs,		/* A gázkeverék specifikus gázállandója */
//			dm,		/* A gáz tömegváltozása dt idő alatt */
//			dV,		/* A gáz térfogatváltozása dt idő alatt */
//			dU,		/* A gáz belső energiájának megváltozása dt idő alatt */
//			T0,		/* A gáz kezdeti hőmérséklete */
//			Te0,
//			T,			/* A gáz hőmérséklete */
//			p,			/* A gáz nyomása */
//			p0,		/* A gáz kezdeti nyomása */
//			v0,		/* A gáz kezdeti fajtérfogata */
//			M,
//			p_ism,
//			T_ism,
//			m_ism,
//			pinmert,
//			s,			/* fajlagos entrópia */
//			S,			/* entrópia */
//			pk[SZELEPEK],	/* A szelepek előtti légnyomás */
//			dV_A,		/* Az adiabatikus kompresszió miatti térfogatváltozás (Teszt) */
//			szv[SZELEPEK]; /* A szelepek előtti levegő fajtérfogata */
	
	double cp,cv,	
			kappa,	
			m,		
			V,      
			v,		
			U,		
			Rs,		
			dm,		
			dV,		
			dU,		
			T0,		
			Te0,
			T,		
			p,		
			p0,		
			v0,		
			M,
			p_ism,
			T_ism,
			m_ism,
			pinmert,
			s,		
			S,	
			pk[] = new double[SZELEPEK],
			dV_A,		
			szv[] = new double[SZELEPEK];

//	/* Geometriai jellemzők */
//	valos r,			/* A forgattyúsugár */
//			l,			/* A hajtórúd hossza */
//			lambda,	/* r/l */
//			d,			/* A dugattyú átmérője */
//			H,			/* A függőleges helyzete  */
//			Aheng,	/* A henger felülete */
//			Ahfej,	/* A hengerfej felülete */
//			Adug,		/* A dugattyú felülete */
//			Vc,		/* A kompressziótérfogat */
//			sin2fi;	/* sin(fi)^2 segédváltozó a dugattyú helyzetének kiszámításához */	

	double r,		
			l,		
			lambda,
			d,		
			H,		
			Aheng,	
			Ahfej,	
			Adug,	
			Vc,		
			sin2fi;
	
//	valos nyit[SZELEPEK],		/* A szelepek nyitása, fokban */
//	zar[SZELEPEK],		/* A szelepek zárása, fokban */
//	nyitva[SZELEPEK],	/* A szelepek nyitvatartása, fokban */
//	szD[SZELEPEK],		/* A szelepek átmérője */
//	szK[SZELEPEK],		/* A szelepek kerülete */
//	szH[SZELEPEK],		/* A szelepek nyitása méterben */
//	szT[SZELEPEK],		/* A szelepek előtti levegő hőmérséklete */
//	szCS[SZELEPEK],		/* A szelepek csillapótétényezője */
//	dmdm[SZELEPEK],		/* A beáramlott levegő tömege az előző időintervallumban */
//	szelepM,		/* A nyitási karakterisztika tényezője */
//	szA[SZELEPEK],		/* Az effektív szelepkeresztmetszet */
//	szAlak[SZELEPEK],	/* A szelepek alaktényezője */
//	xi;			/* Egyenértékű szelepkeresztmetszet */

	double nyit[] = new double[SZELEPEK],	
			zar[] = new double[SZELEPEK],	
			nyitva[] = new double[SZELEPEK],
			szD[] = new double[SZELEPEK],	
			szK[] = new double[SZELEPEK],	
			szH[] = new double[SZELEPEK],	
			szT[] = new double[SZELEPEK],	
			szCS[] = new double[SZELEPEK],	
			dmdm[] = new double[SZELEPEK],	
			szelepM,		
			szA[] = new double[SZELEPEK],	
			szAlak[] = new double[SZELEPEK],
			xi;			

//	/* Egyéb jellemzők */
//	valos fok,  	/* A főtengely helyzete, fokban. 0: szelepváltás a felső holtpontban */
//			fi,		/* A főtengely helyzete, radiánban */
//			dt,		/* Az időintervallum hossza */
//			dfi,		/* A főtengely elfordulása dt idő alatt, radiándan */
//			n,			/* A fordulatszám, 1/min-ben */
//			dfok,		/* A főtengely elfordulása dt idő alatt, fokban */
//			hiba,		/* Az újraszámolás kritériuma */
//			stop,		/* Az a szög, ahol megíllítható a program. (Teszt) */
//			x[SZELEPEK],		/* A szelepek nyitásának aránya */
//			ell,		/* Teszt */
//			toltes=1.0,	/* Pillanatnyi töltési fok. (Teszt) */
//			p_piezo,
//			lamheng,	/* A hengerfal hővezetési tényezője */
//			lamhfej,	/* A hengerfej hővezetési tényezője */
//			lamdug,	/* A dugattyú hővezetési tényezője */
//			alviz,	/* A hűtőközegoldali hőszállítási tényező */
//			allev,	/* A munkaközegoldali hőszállítási tényező */
//			dfalheng,/* A hengerfal átlagos falvastagsága */
//			dfalhfej,/* A hengerfej átlagos falvastagsága */
//			dfaldug,	/* A dugattyútető átlagos falvastagsága */
//			qszheng,	/* a hőszállítás sebessége a hengeren*/
//			qszhfej,	/* a hőszállítás sebessége a hengerfejen*/
//			qszdug,	/* a hőszállítás sebessége a dugattyún*/
//			Thk;		/* A hűtőközeg hőmérséklete */
	
	double fok,  	
			fi,		
			dt,		
			dfi,	
			n,		
			dfok,	
			hiba,	
			stop,	
			x[] = new double[SZELEPEK],
			ell,	
			toltes=1.0,
			p_piezo,
			lamheng,
			lamhfej,
			lamdug,	
			alviz,	
			allev,	
			dfalheng,
			dfalhfej,
			dfaldug,
			qszheng,
			qszhfej,
			qszdug,	
			Thk;	

//	int ismetles,
//	 trace,	/* Nyomkövetés (Teszt) */
//	 outint;	/* A kiírások száma */

	int ismetles,
		 trace,	
		 outint;
	
//	/* Modulok szabályzása */
//	valos	Mod_additiv, 		/* Additív vagy egymás utáni számítás */
//			Mod_ho, 				/* Hőveszteség számítása */
//			Mod_eges, 			/* égés számítása */
//			Mod_karter;			/* kartergáz számítása */

	int Mod_additiv,
			Mod_ho, 	
			Mod_eges, 	
			Mod_karter;	

//	/* égésjellemzők */
//	valos legfel,			/* Légfeleslegtényező */
//			Egesho=41e6, /* A tüzelőanyag égéshője */
//			dm_eg,			/* Egy időegység alatt elégett tüza. tömege */
//			eges_szoge=.010,
//			t_ig,				/* A gyúlási késedelem ideje */
//			be_szog=5.0,	/* Az előbefecskendezési szög */
//			tuz_szog,      /* A tüza. meggyulladásának helye */
//			GyulasModel=0,	/* A gyulladási késedelem sorszáma */
//			EgesModel=0,	/* az égésmodell sorszáma */
//			Meres,			/* Hőfelszbadulás mérése */
//			dHTR=0.0,		/* Hőfelszabadulás */
//			pOxigen,
//			VIBE2_Pm=.15,
//			VIBE2_Pt=.2,
//			VIBE2_Ap=6.9,
//			VIBE2_mp=.5,
//			VIBE2_Qp,
//			VIBE2_tp,
//			VIBE2_Ad=6.9,
//			VIBE2_md=1.0,
//			VIBE2_Qd,
//			VIBE2_td,
//			GYULLAD_SZOG=355.0,
//			WOLFER_E0=38542.0,
//			WOLFER_A1=0.44,
//			WOLFER_n=1.19,
//			SCHMIDT_c=0.03,
//			SCHMIDT_n=1.19,
//			SCHMIDT_E0=38542.0,
//			BAERT_t0=0.86,
//			BAERT_A=4.13,
//			BAERT_B=10557.0,
//			BAERT_n1=0.5,
//			BAERT_C=1900.0,
//			BAERT_n2=-2.1,
//			LISEVSZKIJ_A=206302.0,
//			LISEVSZKIJ_C=78.0, /*Cetánszám*/
//			LISEVSZKIJ_k=-0.9,
//			LISEVSZKIJ_epsz=0.09, /*Kompresszióviszony*/
//			LISEVSZKIJ_l=-1.25,
//			LISEVSZKIJ_m=0.6,
//			LISEVSZKIJ_n=-0.4,
//			LISEVSZKIJ_p=-3.2,
//			KADOTA_z=2.5,
//			KADOTA_p=-2.5,
//			KADOTA_fi=-1.4,
//			KADOTA_E=5000.0,
//			VIBE_m=.5,
//			VIBE_A=6.908,
//			WHITEHOUSE_K=0.01,
//			WHITEHOUSE_x=2.0/3.0,
//			WHITEHOUSE_m=0.4,
//			WHITEHOUSE_Kr=1e2,
//			WHITEHOUSE_act=15000,
//			WHITEHOUSE_a=-4,
//			WHITEHOUSE_C=.2,
//			WHITEHOUSE_dR=0.0,
//			WHITEHOUSE_dP=0.0,
//			Minj,
//			Mu,
//			P,
//			R,
//			dMinj,
//			dMu,
//			dP,
//			dR;
	
	double legfel,
			Egesho=41e6,
			dm_eg,
			eges_szoge=.010,
			t_ig,
			be_szog=5.0,
			tuz_szog,
			dHTR=0.0,
			pOxigen,
			VIBE2_Pm=.15,
			VIBE2_Pt=.2,
			VIBE2_Ap=6.9,
			VIBE2_mp=.5,
			VIBE2_Qp,
			VIBE2_tp,
			VIBE2_Ad=6.9,
			VIBE2_md=1.0,
			VIBE2_Qd,
			VIBE2_td,
			GYULLAD_SZOG=355.0,
			WOLFER_E0=38542.0,
			WOLFER_A1=0.44,
			WOLFER_n=1.19,
			SCHMIDT_c=0.03,
			SCHMIDT_n=1.19,
			SCHMIDT_E0=38542.0,
			BAERT_t0=0.86,
			BAERT_A=4.13,
			BAERT_B=10557.0,
			BAERT_n1=0.5,
			BAERT_C=1900.0,
			BAERT_n2=-2.1,
			LISEVSZKIJ_A=206302.0,
			LISEVSZKIJ_C=78.0,
			LISEVSZKIJ_k=-0.9,
			LISEVSZKIJ_epsz=0.09,
			LISEVSZKIJ_l=-1.25,
			LISEVSZKIJ_m=0.6,
			LISEVSZKIJ_n=-0.4,
			LISEVSZKIJ_p=-3.2,
			KADOTA_z=2.5,
			KADOTA_p=-2.5,
			KADOTA_fi=-1.4,
			KADOTA_E=5000.0,
			VIBE_m=.5,
			VIBE_A=6.908,
			WHITEHOUSE_K=0.01,
			WHITEHOUSE_x=2.0/3.0,
			WHITEHOUSE_m=0.4,
			WHITEHOUSE_Kr=1e2,
			WHITEHOUSE_act=15000,
			WHITEHOUSE_a=-4,
			WHITEHOUSE_C=.2,
			WHITEHOUSE_dR=0.0,
			WHITEHOUSE_dP=0.0,
			Minj,
			Mu,
			P,
			R,
			dMinj,
			dMu,
			dP,
			dR;

	int GyulasModel=0,
		EgesModel=0,
		Meres;

//	int	egesallapot;	/* ég, vagy nem ég a tüza. */
	
	int	egesallapot;

//	valos m_adag,bef_kar=3.0e-6,tz;
	
	double m_adag,bef_kar=3.0e-6,tz;
	
//	/* Gázcsere */
//	valos dm_sz[SZELEPEK],	/* A kiáramló gáz tömege dt idő alatt, szelpenként */
//			dU_sz,		/* A gázcsere következtében megváltozó belső energia dt idő alatt, szelepenként */
//			minimum,		/* Két szög közül a kissebb  */
//			maximum,    /* Két szög közül a nagyobb */
//			dm_V[SZELEPEK];		/* Az a légtömeg, amely akkor áramlana be, ha nem lenne fojtás a szelepeknél */

	double dm_sz[] = new double[SZELEPEK],	
			dU_sz, 
			minimum,		
			maximum, 
			dm_V[] = new double[SZELEPEK];

//	char	inname[MAXPATH+MAXFILE+MAXEXT],
//			outname[MAXPATH+MAXFILE+MAXEXT],
//			mertname[MAXPATH+MAXFILE+MAXEXT], /* A be- és kimeneti file neve */
//			poutname[MAXPATH+MAXFILE+MAXEXT],
//			pinname[MAXPATH+MAXFILE+MAXEXT],
//			HTRinname[MAXPATH+MAXFILE+MAXEXT],
//			HTRname[MAXPATH+MAXFILE+MAXEXT],
//			modositname[MAXPATH+MAXFILE+MAXEXT];

	String inname,
			outname,
			mertname,
			poutname,
			pinname,
			HTRinname,
			HTRname,
			modositname;
	
//	file in,		/* bemeneti file */
//		  out,	/* kimeneti file */
//		  mert,  /* meresi eredmény */
//		  ment,	/* egy állapot összes változóját lementő file */
//		  HTRout,
//		  HTRin,
//		  pin,
//		  pout,
//		  modin;
	
	BufferedReader in;	
	PrintWriter out;	
	BufferedReader mert; 
	PrintStream ment;	
	PrintStream HTRout;
	BufferedReader HTRin;
	BufferedReader pin;
	PrintStream pout;
	BufferedReader modin;

//	_VPtip p_f,	p_V, T_s, p_HTR, p_Mu, p_P, p_R, p_T, pp_f, HTR_f;	/* A grafikonok */
//	F2D	kozep;
//	int	Mod_Ablakok, Mod_HTR;
//	valos	FTmin,FTmax,FToszt;	
	
	Chart p_f,	p_V, T_s, p_HTR, p_Mu, p_P, p_R, p_T, pp_f, HTR_f;
	int	Mod_Ablakok, Mod_HTR;
	double FTmin,FTmax,FToszt;	
	
//	char SB[128];	/* egy fileból beolvasott szám */
//	double aSertek;
//	char* aSnev;
//	#define aSmax 99
//	struct { char* nev[aSmax];
//				valos ertek[aSmax];
//			 } aST;
//	int kie=0,moi; /* Kiírás figyelése */
//	int HTRni;
	
	String SB;
	double aSertek;
	String aSnev;
	final int aSmax = 99;
	Map<String,Double> aST = new HashMap <String,Double>();
	int kie=0,moi;
	int HTRni;
	
//	#include "eges46.c"
	
//	#define GYULLAD360	0
//	#define WOLFER 		1
//	#define SCHMIDT 		2
//	#define BAERT 			3
//	#define LISEVSZKIJ	4
//	#define KADOTA 		5

	final int GYULLAD360 = 0;
	final int WOLFER = 1;
	final int SCHMIDT = 2;
	final int BAERT = 3;
	final int LISEVSZKIJ = 4;
	final int KADOTA = 5;
	
//	#define VIBE 0
//	#define DUPLAVIBE 1
//	#define WHITEHOUSE 2

	final int VIBE = 0;
	final int DUPLAVIBE = 1;
	final int WHITEHOUSE = 2;

//	#define NINCS		0
//	#define KESIK		1
//	#define EG			2
//	#define ELEGETT	3

	final int NINCS = 0;
	final int KESIK	= 1;
	final int EG = 2;
	final int ELEGETT = 3;
	
//	void gyullad360(),wolfer(),schmidt(),baert(),lisevszkij(),kadota();
//	void ( *gyulasalgoritmusok[] )()={gyullad360,wolfer,schmidt,baert,lisevszkij,kadota};
//	valos vibe(),duplavibe(),whitehouse();
//	valos ( *egesalgoritmusok[] )()={vibe,duplavibe,whitehouse};
	
	public interface gyulasalgoritmus
	{
		public void gyullad();
	}
	
/*
		gyulasalgoritmusok[GYULLAD360] = new gyullad360();
		gyulasalgoritmusok[WOLFER] = new wolfer();
		gyulasalgoritmusok[SCHMIDT] = new schmidt();
		gyulasalgoritmusok[BAERT] = new baert();
		gyulasalgoritmusok[LISEVSZKIJ] = new lisevszkij();
		gyulasalgoritmusok[KADOTA] = new kadota();

		egesalgoritmusok[VIBE] = new vibe();
		egesalgoritmusok[DUPLAVIBE] = new duplavibe();
		egesalgoritmusok[WHITEHOUSE] = new whitehouse();			
 */
	gyulasalgoritmus[] gyulasalgoritmusok = { new gyullad360(), new wolfer(), new schmidt(),
			new baert(), new lisevszkij(), new kadota() };
	
	public interface egesalgoritmus
	{
		public double eg();
	}	
	
	egesalgoritmus[] egesalgoritmusok = {new vibe(), new duplavibe(), new whitehouse()};
	
//	void gyullad360()
//	{
//		if( t_ig==0.0 )
//		{	/*fprintf(out,"Gyullad:360\n");*/
//			t_ig=1.0;
//			tuz_szog=GYULLAD_SZOG; /*-be_szog+t_ig*6.0*n;*/
//		}
//		else
//			if( tuz_szog<fok )
//				egesallapot=EG;
//	}
	
	public class gyullad360 implements gyulasalgoritmus
	{
		@Override
		public void gyullad() 
		{
			if( t_ig==0.0 )
			{	/*fprintf(out,"Gyullad:360\n");*/
				t_ig=1.0;
				tuz_szog=GYULLAD_SZOG; /*-be_szog+t_ig*6.0*n;*/
			}
			else
				if( tuz_szog<fok )
					egesallapot=EG;			
		}
	}
	
//	void wolfer()
//	{
//		if( t_ig==0.0 )
//		{	fprintf(out,"Wolfer\n");
//			t_ig=WOLFER_A1*exp(WOLFER_E0/Re/T)/pow(p*1e-5,WOLFER_n)*1e-3;
//			tuz_szog=360.0-be_szog+t_ig*6.0*n;
//	/*		m_adag=m/15.0/legfel;
//	*/	}
//		else
//			if( tuz_szog<fok )
//				egesallapot=EG;
//	}
	
	public class wolfer implements gyulasalgoritmus
	{
		@Override
		public void gyullad() 
		{

			if( t_ig==0.0 )
			{	// fprintf(out,"Wolfer\n");
				t_ig=WOLFER_A1*exp(WOLFER_E0/Re/T)/pow(p*1e-5,WOLFER_n)*1e-3;
				tuz_szog=360.0-be_szog+t_ig*6.0*n;
		/*		m_adag=m/15.0/legfel;
		*/	}
			else
				if( tuz_szog<fok )
					egesallapot=EG;

		}
	}
	
//	void schmidt()
//	{
//		if( egesallapot==NINCS )
//		{	fprintf(out,"Schmidt\n");
//			t_ig=SCHMIDT_c*sqrt(T)/pow(p*1e-5,SCHMIDT_n)*exp(-SCHMIDT_E0/Re/T)*1e-3;
//			tuz_szog=360-be_szog+t_ig*6.0*n;
//		}
//		else
//			if( tuz_szog<fok )
//				egesallapot=EG;
//	}
	
	public class schmidt implements gyulasalgoritmus
	{
		@Override
		public void gyullad() 
		{
			if( egesallapot==NINCS )
			{	// fprintf(out,"Schmidt\n");
				t_ig=SCHMIDT_c*sqrt(T)/pow(p*1e-5,SCHMIDT_n)*exp(-SCHMIDT_E0/Re/T)*1e-3;
				tuz_szog=360-be_szog+t_ig*6.0*n;
			}
			else
				if( tuz_szog<fok )
					egesallapot=EG;
		}
	}	

//	void baert()
//	{
//		static valos t1,t2;
//
//		if( egesallapot==NINCS )
//		{	fprintf(out,"Baert\n");
//			t1=exp(BAERT_B/t1)*pow(p*1e-5,-BAERT_n1);
//			t2=BAERT_C*pow(p*1e-5,-BAERT_n2);
//			t_ig=BAERT_t0+BAERT_A*exp(BAERT_B/t1)*pow(p*1e-5,-BAERT_n1)+BAERT_C*pow(p*1e-5,-BAERT_n2)*1e-3;
//			tuz_szog=360-be_szog+t_ig*n*6.0;
//		}
//		else
//			if( tuz_szog<fok )
//				egesallapot=EG;
//	}

	/*
	 * Corrected: t1 --> T, t1, t2 removed
	 * 
	 */
	public class baert implements gyulasalgoritmus
	{
		@Override
		public void gyullad() 
		{
			if( egesallapot==NINCS )
			{	// fprintf(out,"Baert\n");
				t_ig=BAERT_t0+BAERT_A*exp(BAERT_B/T)*pow(p*1e-5,-BAERT_n1)+BAERT_C*pow(p*1e-5,-BAERT_n2)*1e-3;
				tuz_szog=360-be_szog+t_ig*n*6.0;
			}
			else
				if( tuz_szog<fok )
					egesallapot=EG;
		}
	}	

//	void lisevszkij()
//	{
//		if( t_ig==0.0 )
//		{	fprintf(out,"Lisevszkij\n");
//			/* az égésidő [ms]-ban */
//			t_ig=1/n*LISEVSZKIJ_A*pow(LISEVSZKIJ_C,LISEVSZKIJ_k)*pow(LISEVSZKIJ_epsz,LISEVSZKIJ_l)*pow(be_szog,LISEVSZKIJ_m)*pow(p/p0,LISEVSZKIJ_n)*pow(T/T0,LISEVSZKIJ_p)*1e-3;
//	      tuz_szog=360-be_szog+t_ig*n*6.0;
//		}
//		else
//			if( tuz_szog<fok )
//				egesallapot=EG;
//	}
	
	public class lisevszkij implements gyulasalgoritmus
	{
		@Override
		public void gyullad() 
		{
			if( t_ig==0.0 )
			{	// fprintf(out,"Lisevszkij\n");
				/* az égésidő [ms]-ban */
				t_ig=1/n*LISEVSZKIJ_A*pow(LISEVSZKIJ_C,LISEVSZKIJ_k)*pow(LISEVSZKIJ_epsz,LISEVSZKIJ_l)*pow(be_szog,LISEVSZKIJ_m)*pow(p/p0,LISEVSZKIJ_n)*pow(T/T0,LISEVSZKIJ_p)*1e-3;
		      tuz_szog=360-be_szog+t_ig*n*6.0;
			}
			else
				if( tuz_szog<fok )
					egesallapot=EG;
		}
	}	

//	void kadota()
//	{
//		static valos KADOTA_legfelesleg,dt;
//
//		if( egesallapot==NINCS )
//			fprintf(out,"Kadota\n");
//		KADOTA_legfelesleg=0.1;
//		t_ig+=1/(KADOTA_z/1E3*pow(p*1e-5,-KADOTA_p)*pow(KADOTA_legfelesleg,-KADOTA_fi)*exp(KADOTA_E/T))*dt;
//		if( t_ig>1.0 )
//			egesallapot=EG;
//	}
	
	public class kadota implements gyulasalgoritmus
	{
		@Override
		public void gyullad() 
		{
			double KADOTA_legfelesleg, dt = 0.;

			if( egesallapot==NINCS ) {
			//	fprintf(out,"Kadota\n");
			}
			KADOTA_legfelesleg=0.1;
			t_ig+=1/(KADOTA_z/1E3*pow(p*1e-5,-KADOTA_p)*pow(KADOTA_legfelesleg,-KADOTA_fi)*exp(KADOTA_E/T))*dt;
			if( t_ig>1.0 )
				egesallapot=EG;			
		}
	}	

//	valos vibe()
//	{
//		static valos dx;
//	/*
//		B4: [W9] 1-@EXP(-$VIBE_A*A4^($VIBE_M+1))
//		C4: +$VIBE_A*($VIBE_M+1)*A4^$VIBE_M*@EXP(-$VIBE_A*A4^($VIBE_M+1))
//		VIBE_A*m_adag/eges_szoge*(VIBE_M+1)*POW((fok-tuz_szog)/eges_szoge,VIBE_M)*EXP(-VIBE_A*POW((fok-tuz_szog)/eges_szoge,VIBE_M))
//	*/
//
//	/*	tz=((fok-tuz_szog)/6.0/n)/eges_ideje;
//		dx=VIBE_A*(VIBE_m+1)*pow(tz,VIBE_m)*exp(-VIBE_A*pow(tz,VIBE_m+1))/eges_ideje*m_adag*dt;
//		69/10*qp/op*(mp+1)*POW(o/op,mp)*EXP(-69/10*POW(o/op,mp))
//	*/
//		tz=(fok-tuz_szog)/eges_szoge;
//		dx=VIBE_A*m_adag/eges_szoge*(VIBE_m+1)*pow(tz,VIBE_m)*exp(-VIBE_A*pow(tz,VIBE_m+1))*dfok;
//
//		if( fok>360.0+eges_szoge-be_szog )
//			egesallapot=ELEGETT;
//
//		return( dx );
//	}
	
	public class vibe implements egesalgoritmus
	{
		@Override
		public double eg() 
		{
			double dx;
			
			tz=(fok-tuz_szog)/eges_szoge;
			dx=VIBE_A*m_adag/eges_szoge*(VIBE_m+1)*pow(tz,VIBE_m)*exp(-VIBE_A*pow(tz,VIBE_m+1))*dfok;

			if( fok>360.0+eges_szoge-be_szog )
				egesallapot=ELEGETT;

			return( dx );
		}
		
	}

//	valos duplavibe()
//	{
//		static valos t,p,d,dx;
//
//	/*
//		(1-EXP(-ap*POW(t/tp,mp+1)))*qp
//		(1-EXP(-ad*POW(t/td,md+1)))*qd
//		qp-qp*POW(#e,-ap*t*POW(t/tp,mp)/tp)
//		qd-qd*POW(#e,-ad*t*POW(t/td,md)/td)
//		DIF(qp-qp*POW(#e,-ap*t*POW(t/tp,mp)/tp),t)
//		DIF(qd-qd*POW(#e,-ad*t*POW(t/td,md)/td),t)
//		ap*qp*(mp+1)*POW(#e,mp*LN(t/tp)-ap*t*POW(t/tp,mp)/tp)/tp
//		ad*qd*(md+1)*POW(#e,md*LN(t/td)-ad*t*POW(t/td,md)/td)/td
//
//	#define VIBE2_PM
//	#define VIBE2_Ap
//	#define VIBE2_mp
//	#define VIBE2_Qp
//	#define VIBE2_tp
//	#define VIBE2_Ad
//	#define VIBE2_md
//	#define VIBE2_Qd
//	#define VIBE2_td
//	*/
//
//		t=fok-tuz_szog;
//
//		if( t<=VIBE2_tp )
//			p=VIBE2_Ap*VIBE2_Qp*(VIBE2_mp+1.0)*exp(VIBE2_mp*log(t/VIBE2_tp)-VIBE2_Ap*t*pow(t/VIBE2_tp,VIBE2_mp)/VIBE2_tp)/VIBE2_tp;
//		else
//			p=0.0;
//		d=VIBE2_Ad*VIBE2_Qd*(VIBE2_md+1.0)*exp(VIBE2_md*log(t/VIBE2_td)-VIBE2_Ad*t*pow(t/VIBE2_td,VIBE2_md)/VIBE2_td)/VIBE2_td;
//
//		dx=(p+d)*dfok;
//
//		if( fok>360.0+eges_szoge-be_szog )
//			egesallapot=ELEGETT;
//
//		return( dx );
//
//	}
	
	public class duplavibe implements egesalgoritmus
	{
		@Override
		public double eg() 
		{
			double t,p,d,dx;
			
			t=fok-tuz_szog;

			if( t<=VIBE2_tp )
				p=VIBE2_Ap*VIBE2_Qp*(VIBE2_mp+1.0)*exp(VIBE2_mp*log(t/VIBE2_tp)-VIBE2_Ap*t*pow(t/VIBE2_tp,VIBE2_mp)/VIBE2_tp)/VIBE2_tp;
			else
				p=0.0;
			d=VIBE2_Ad*VIBE2_Qd*(VIBE2_md+1.0)*exp(VIBE2_md*log(t/VIBE2_td)-VIBE2_Ad*t*pow(t/VIBE2_td,VIBE2_md)/VIBE2_td)/VIBE2_td;

			dx=(p+d)*dfok;

			if( fok>360.0+eges_szoge-be_szog )
				egesallapot=ELEGETT;

			return( dx );
		}
	}
	
//	valos whitehouse()
//	{
//		static valos dx,e,s;
//
//	/*	if( Minj>=m_adag )
//			dx=0.0;
//		else
//	*/	{
//			if( m_adag>=Minj )
//			{	if( m_adag-Minj>bef_kar*dfok )
//					dMinj=bef_kar*dfok;
//				else
//					dMinj=m_adag-Minj;
//			}
//			else
//				dMinj=0.0;
//			Minj+=dMinj;
//			Mu+=dMinj;
//			pOxigen=p*qi[OXIGEN]/Mi[OXIGEN];
//			if( Mu>0.0 )
//			{	/*dP=WHITEHOUSE_K*pow(Minj,1.0-WHITEHOUSE_x)*pow(Mu,WHITEHOUSE_x)*pow(pOxigen*1e-5,WHITEHOUSE_m);*/
//				dP=WHITEHOUSE_K*pow(Minj,1.0-WHITEHOUSE_x)*pow(Mu,WHITEHOUSE_x)*pow(p*1e-5,WHITEHOUSE_m)*dfok;
//				dP=WHITEHOUSE_dP+(dP-WHITEHOUSE_dP)*WHITEHOUSE_C;
//				if( dP>Mu )
//					dP=Mu;
//				WHITEHOUSE_dP=dP;
//				P+=dP;
//				Mu-=dP;
//			}
//			if( egesallapot==EG )
//			{  e=exp(-WHITEHOUSE_act/T /*+WHITEHOUSE_a*/);
//				s=(pOxigen*1e-5)/sqrt(T);
//				dR=WHITEHOUSE_Kr*s*e*P*dfok/n;
//	/*							dm_sz[i]=dmdm[i]+(dm_sz[i]-dmdm[i])*szCS[i];	/* Csillapátás */*/
//				dR=WHITEHOUSE_dR+(dR-WHITEHOUSE_dR)*WHITEHOUSE_C;
//				if( dR*3.36>(mi[OXIGEN]-dmi[OXIGEN]) )
//					dR=(mi[OXIGEN]-dmi[OXIGEN])/3.36*.99;
//	/*			else
//		*/		if( dR>=P )
//					dR=P;
//				WHITEHOUSE_dR=dR;
//				R+=dR;
//				P-=dR;
//				dx=dR;
//			}
//			if( Mu>0.0 )
//			{	P+=dP;
//				Mu-=dP;
//			}
//		}
//
//		if( fok>nyit[KIPUFOGO] )
//			egesallapot=ELEGETT;
//
//		return( dx );
//
//	}	
	
	public class whitehouse implements egesalgoritmus
	{
		@Override
		public double eg() 
		{
			double dx=0.0,e,s;
			
			if( m_adag>=Minj )
			{	if( m_adag-Minj>bef_kar*dfok )
					dMinj=bef_kar*dfok;
				else
					dMinj=m_adag-Minj;
			}
			else
				dMinj=0.0;
			Minj+=dMinj;
			Mu+=dMinj;
			pOxigen=p*qi[OXIGEN]/Mi[OXIGEN];
			if( Mu>0.0 )
			{	/*dP=WHITEHOUSE_K*pow(Minj,1.0-WHITEHOUSE_x)*pow(Mu,WHITEHOUSE_x)*pow(pOxigen*1e-5,WHITEHOUSE_m);*/
				dP=WHITEHOUSE_K*pow(Minj,1.0-WHITEHOUSE_x)*pow(Mu,WHITEHOUSE_x)*pow(p*1e-5,WHITEHOUSE_m)*dfok;
				dP=WHITEHOUSE_dP+(dP-WHITEHOUSE_dP)*WHITEHOUSE_C;
				if( dP>Mu )
					dP=Mu;
				WHITEHOUSE_dP=dP;
				P+=dP;
				Mu-=dP;
			}
			if( egesallapot==EG )
			{  e=exp(-WHITEHOUSE_act/T /*+WHITEHOUSE_a*/);
				s=(pOxigen*1e-5)/sqrt(T);
				dR=WHITEHOUSE_Kr*s*e*P*dfok/n;
	/*							dm_sz[i]=dmdm[i]+(dm_sz[i]-dmdm[i])*szCS[i];	// Csillapítás */
				dR=WHITEHOUSE_dR+(dR-WHITEHOUSE_dR)*WHITEHOUSE_C;
				if( dR*3.36>(mi[OXIGEN]-dmi[OXIGEN]) )
					dR=(mi[OXIGEN]-dmi[OXIGEN])/3.36*.99;
	/*			else
		*/		if( dR>=P )
					dR=P;
				WHITEHOUSE_dR=dR;
				R+=dR;
				P-=dR;
				dx=dR;
			}
			if( Mu>0.0 )
			{	P+=dP;
				Mu-=dP;
			}


			if( fok>nyit[KIPUFOGO] )
				egesallapot=ELEGETT;
		
			return( dx );
		}
	}
	
//	eges()
//	{
//		switch( egesallapot )
//		{  case NINCS:
//						if( fok>=360.0-be_szog )
//						{	egesallapot=KESIK;
//							t_ig=0.0;
//						}
//						break;
//
//			case KESIK:
//						(*gyulasalgoritmusok[ GyulasModel ])();
//						if( EgesModel==WHITEHOUSE )
//							whitehouse();
//						break;
//
//			case EG:
//						dm_eg=(*egesalgoritmusok[ EgesModel ])();
//						dmi[OXIGEN]+=-dm_eg*3.36;			/* O//2	*/
//						dmi[3]+=dm_eg*(.87+2.32);	/* CO//2	*/
//						dmi[4]+=dm_eg*(.13+1.04);	/* H//2O	*/
//	/*					dm+=dm_eg;	*/
//						dU+=dm_eg*Egesho;
//						break;
//			case ELEGETT:
//						t_ig=t_ig*1.0;
//		}
//	}
	
	double eges()
	{
		switch( egesallapot )
		{  case NINCS:
						if( fok>=360.0-be_szog )
						{	egesallapot=KESIK;
							t_ig=0.0;
						}
						break;

			case KESIK:
						gyulasalgoritmusok[ GyulasModel ].gyullad();
						if( EgesModel==WHITEHOUSE )
							egesalgoritmusok[ EgesModel ].eg();;
						break;

			case EG:
						dm_eg=egesalgoritmusok[ EgesModel ].eg();
						dmi[OXIGEN]+=-dm_eg*3.36;			/* O//2	*/
						dmi[3]+=dm_eg*(.87+2.32);	/* CO//2	*/
						dmi[4]+=dm_eg*(.13+1.04);	/* H//2O	*/
	/*					dm+=dm_eg;	*/
						dU+=dm_eg*Egesho;
						break;
			case ELEGETT:
						t_ig=t_ig*1.0;
		}
		
		return dm_eg;
	}
	
	/*
	main()
	{
		dt=osztas/n/6.0;
		m=.01;

		fprintf(out,"main\n");
		for(fok=340.0; fok<720.0; fok+=osztas)
		{	fprintf(out,"%g %g\n",fok,eges());
		}
	}
	*/	
	
	void test_eges()
	{
		dt=dfok/n/6.0;
		m=.01;

		fprintf(out,"main\n");
		for(fok=340.0; fok<720.0; fok+=dfok)
		{	
			out.printf("%g %g\n",fok,eges());
		}
	}	
	
//	valos valosbe( file be ) /* Egy valós szám bekérése */
//	{
//		while ( (getc(be)) != '=');
//		fscanf(be, "%s",SB );
//		return( atof(SB) );
//	}

//	valos gyok( valos x )	/* Gyökvonás */
//	{
//		if( x<0.0 )
//			return(sqrt(-(x)));
//		else
//			return(sqrt(x));
//	}
	
	double gyok( double x )	/* Gyökvonás */
	{
		if( x<0.0 )
			return(sqrt(-(x)));
		else
			return(sqrt(x));
	}
	
//	/*
//	int matherr( struct exception *e )
//	{
//		static valos c;
//
//		if( strcmp(e->name,"log")==0 && e->arg1<0.0 )
//		{	e->retval=0.0;
//			printf( "programhiba: negatív alap\n" );
//		}
//		else
//			exit(1);
//		return( 1 );
//	}
//	*/
	
//	valos S_c( komponens q, komponens c )	/* A gázelegy egy eredő jellemzőjét */
//	{													/* számítja ki a tömegrészek és 		*/
//		static valos S;							/* az alkotók jellemzőinek összeszor*/
//		static int i;								/* zása alapján */
//
//		S=0.0;
//		for( i=0; i<KOMPONENSEK; i++ )
//			S+=q[i]*c[i];
//		return( S );
//	}

	double S_c( double[] q, double[] c )	/* A gázelegy egy eredő jellemzőjét */
	{													/* számítja ki a tömegrészek és 		*/
		double S;							/* az alkotók jellemzőinek összeszor*/
		int i;								/* zása alapján */

		S=0.0;
		for( i=0; i<KOMPONENSEK; i++ )
			S+=q[i]*c[i];
		return( S );
	}
	
//	valos terfogat()								/* A pillanatnyi hengertérfogat */
//	{
//		float V;
//
//		V=Vc+Adug*H;
//		return(V);
//	}
	
	double terfogat()								/* A pillanatnyi hengertérfogat */
	{
		double V;

		V=Vc+Adug*H;
		return(V);
	}

//	hocsere()                        /* Hőátadás számítása */
//	{
//		static valos deltat;
//
//		deltat=Thk-T;
//		dU+=(Aheng*qszheng+Ahfej*qszhfej+Adug*qszdug)*p*1e-6*deltat*dt;
//	}
	
	void hocsere()                        /* Hőátadás számítása */
	{
		double deltat;

		deltat=Thk-T;
		dU+=(Aheng*qszheng+Ahfej*qszhfej+Adug*qszdug)*p*1e-6*deltat*dt;
	}	

//	valos tomegaram( A, p1, p2, v1, v2 )	/* Tömegáram számítása a Hőtan*/
//	valos A,p1,p2,v1,v2;                   /* jegyzet alapján */
//	{
//		static valos Gm,p,v;
//
//		if( p1>p2 )
//			A=-A;	/* Kiáramlás */
//		else		/* Beáramlás */
//		{	p=p1;		/* p1<==>p2 */
//			p1=p2;
//			p2=p;
//			v=v1;		/* v1<==>v2 */
//			v1=v2;
//			v2=v;
//		}
//		Gm=M_SQRT2*A*gyok(kappa*(p1*pow(p2/p1,2.0/kappa)-p2*pow(p2/p1,1.0/kappa))/(v1*(kappa-1.0)));
//
//		return( Gm );
//	}

	final double M_SQRT2 = sqrt(2.0);
	
	double tomegaram( double A, double p1, double p2, double v1, double v2 )	/* Tömegáram számítása a Hőtan*/
	{
		double Gm,p,v;

		if( p1>p2 )
			A=-A;	/* Kiáramlás */
		else		/* Beáramlás */
		{	p=p1;		/* p1<==>p2 */
			p1=p2;
			p2=p;
			v=v1;		/* v1<==>v2 */
			v1=v2;
			v2=v;
		}
		Gm=M_SQRT2*A*gyok(kappa*(p1*pow(p2/p1,2.0/kappa)-p2*pow(p2/p1,1.0/kappa))/(v1*(kappa-1.0)));

		return( Gm );
	}

//	valos szelepkeresztmetszet(int SZ)	/* A megadott szelep pillanatnyi */
//	{                                   /* beömlőkeresztmetszete */
//		static valos _A, AA, a, aa;
//
//		{  if( fok>=nyit[SZ] )
//				x[SZ]=(fok-nyit[SZ])/nyitva[SZ];
//			else
//				x[SZ]=(zar[SZ]-fok)/nyitva[SZ];
//
//			AA=szK[SZ]*szH[SZ];
//			aa=x[SZ]*M_PI*2.0;
//			a=pow((1.0-cos(aa))/2.0,szelepM);
//			_A=AA*a;
//
//			return( _A );
//		}
//	}
	
	final double M_PI = Math.PI;
	
	double szelepkeresztmetszet(int SZ)	/* A megadott szelep pillanatnyi */
	{                                   /* beömlőkeresztmetszete */
		double _A, AA, a, aa;

		{  if( fok>=nyit[SZ] )
				x[SZ]=(fok-nyit[SZ])/nyitva[SZ];
			else
				x[SZ]=(zar[SZ]-fok)/nyitva[SZ];

			AA=szK[SZ]*szH[SZ];
			aa=x[SZ]*M_PI*2.0;
			a=pow((1.0-cos(aa))/2.0,szelepM);
			_A=AA*a;

			return( _A );
		}
	}
	
//	gazcsere() /* A két szelepnél ki- és beáramló gázok számítása */
//	{
//		static int i,j;
//
//		for( i=0; i<SZELEPEK; i++ )	/* Két szelep és a gyűrűhorony van */
//		{  maximum=max(nyit[i],zar[i]);
//			minimum=min(nyit[i],zar[i]);
//			if( (Mod_karter && (i==GYURUHORONY)) || !((fok>minimum) && (fok<maximum)) )	/* Nyitva van-e */
//			{  if( i==GYURUHORONY )
//					if( Mod_karter )
//					{	dm_sz[i]=tomegaram( xi, p, pk[i], v, szv[i] )*dt;
//						dmdm[i]=1.0*dm_sz[i];
//					}
//					else
//						dm_sz[i]=0.0;
//				else /* A két szelep */
//				{	szA[i]=szelepkeresztmetszet( i );      /* A beömlőkeresztmetszet */
//					dm_sz[i]=szAlak[i]*tomegaram( szA[i], p, pk[i], v, szv[i] )*dt;
//																	/* dm=μ·q·dt */
//					dm_sz[i]=dmdm[i]+(dm_sz[i]-dmdm[i])*szCS[i];	/* Csillapítás */
//					/*	dm_V[i]=p0*dV_A/Rs/T0*toltes;*/
//					dmdm[i]=dm_sz[i];								/* A tömeg megjegyzése */
//																	/* a következő pillanatra */
//				}
//
//				dU_sz=0.0;
//				if( dm_sz[i]!=0.0 )  /* Ha van valami */
//					if( dm_sz[i]<0.0 )
//						/* Kiáramlás */
//						dU_sz=-(U-cv*(m+dm_sz[i])*T*pow(1.0+dm_sz[i]/m,kappa-1.0));
//					else
//						/* Beáramlás */
//						dU_sz=(U+cv*dm_sz[i]*szT[i])*(pow(1.0+Rs*szT[i]*dm_sz[i]/V/pk[i],kappa-1.0)/*-1.0*/)-U;
//				dU+=dU_sz;
//				if( dm_sz[i]>0.0 )
//					for( j=0; j<KOMPONENSEK; j++ )
//						dmi[j] += (q0i[j]) * dm_sz[i] ;
//				else
//					for( j=0; j<KOMPONENSEK; j++ )
//						dmi[j] += (qi[j]) * dm_sz[i] ;
//			}
//			else
//				dmdm[i]=0.0;
//		}
//	}

	
	void gazcsere() /* A két szelepnél ki- és beáramló gázok számítása */
	{
		int i,j;

		for( i=0; i<SZELEPEK; i++ )	/* Két szelep és a gyűrűhorony van */
		{  maximum=max(nyit[i],zar[i]);
			minimum=min(nyit[i],zar[i]);
			if( (Mod_karter > 0 && (i==GYURUHORONY)) || !((fok>minimum) && (fok<maximum)) )	/* Nyitva van-e */
			{  if( i==GYURUHORONY )
					if( Mod_karter > 0 )
					{	dm_sz[i]=tomegaram( xi, p, pk[i], v, szv[i] )*dt;
						dmdm[i]=1.0*dm_sz[i];
					}
					else
						dm_sz[i]=0.0;
				else /* A két szelep */
				{	szA[i]=szelepkeresztmetszet( i );      /* A beömlőkeresztmetszet */
					dm_sz[i]=szAlak[i]*tomegaram( szA[i], p, pk[i], v, szv[i] )*dt;
																	/* dm=μ·q·dt */
					dm_sz[i]=dmdm[i]+(dm_sz[i]-dmdm[i])*szCS[i];	/* Csillapítás */
					/*	dm_V[i]=p0*dV_A/Rs/T0*toltes;*/
					dmdm[i]=dm_sz[i];								/* A tömeg megjegyzése */
																	/* a következő pillanatra */
				}

				dU_sz=0.0;
				if( dm_sz[i]!=0.0 )  /* Ha van valami */
					if( dm_sz[i]<0.0 )
						/* Kiáramlás */
						dU_sz=-(U-cv*(m+dm_sz[i])*T*pow(1.0+dm_sz[i]/m,kappa-1.0));
					else
						/* Beáramlás */
						dU_sz=(U+cv*dm_sz[i]*szT[i])*(pow(1.0+Rs*szT[i]*dm_sz[i]/V/pk[i],kappa-1.0)/*-1.0*/)-U;
				dU+=dU_sz;
				if( dm_sz[i]>0.0 )
					for( j=0; j<KOMPONENSEK; j++ )
						dmi[j] += (q0i[j]) * dm_sz[i] ;
				else
					for( j=0; j<KOMPONENSEK; j++ )
						dmi[j] += (qi[j]) * dm_sz[i] ;
			}
			else
				dmdm[i]=0.0;
		}
	}

//	kezdoertekek()	/* Kezdőértékek kiszámítása */
//	{
//		static int i;
//
//		fi=fok=0.0;
//		dfi=dfok/RAD;
//		dt=dfok/(6*n);
//
//		lambda=r/l;
//		sin2fi=sin(fi)*sin(fi);
//		H=r*(1.0-cos(fi)+lambda/2.0*sin2fi+lambda*lambda*lambda/8*sin2fi*sin2fi);
//
//		Ahfej=Adug=d*d*M_PI/4.0;
//		V=terfogat();
//
//		dmdm[0]=dmdm[1]=0.0;
//		szA[0]=szA[1]=0.0;
//
//		for(i=0; i<KOMPONENSEK; i++ )
//		{  dmi[i]=0.0;
//			qi[i]=q0i[i];
//			Mi[i]=M0i[i];
//			Rsi[i]=Re/Mi[i];
//			cvi[i]=Rsi[i]/(kappa0i[i]-1.0);
//			cpi[i]=kappa0i[i]*cvi[i];
//		}
//		cp=S_c(qi,cpi);
//		cv=S_c(qi,cvi);
//		Rs=cp-cv;
//		kappa=cp/cv;
//		T=Te0;
//		p=p0;
//		m=p*V/(T*Rs);
//		U=cv*m*T;
//		v=v0=V/m;
//		M=Rs*m*T/(p*V);
//		pk[SZIVO]=pk[KIPUFOGO]=p0;
//		szv[SZIVO]=szv[KIPUFOGO]=v0;
//		p_ism=p;
//		T_ism=T;
//		m_ism=m;
//
//		for(i=0; i<KOMPONENSEK; i++ )
//		  mi[i]=qi[i]*m;
//
//		for( i=0; i<SZELEPEK; i++ )
//		{	nyitva[i]=720.0-(nyit[i]-zar[i]);
//			szK[i]=szD[i]*M_PI;
//		}
//		nyit[GYURUHORONY]=0.0;
//		zar[GYURUHORONY]=720.0;
//		szA[GYURUHORONY]=xi;
//
//		qszheng=1/(1/alviz+dfalheng/lamheng+1/allev);
//		qszhfej=1/(1/alviz+dfalhfej/lamhfej+1/allev);
//		qszdug=1/(1/alviz+dfaldug/lamdug+1/allev);
//
//		egesallapot=NINCS;
//		VIBE2_Qp=m_adag*VIBE2_Pm;
//		VIBE2_tp=eges_szoge*VIBE2_Pt;
//		VIBE2_Qd=m_adag-VIBE2_Qp;
//		VIBE2_td=eges_szoge;
//		Minj=Mu=P=R=0.0;
//
//	}
	
	void kezdoertekek()	/* Kezdőértékek kiszámítása */
	{
		int i;

		fi=fok=0.0;
		dfi=dfok/RAD;
		dt=dfok/(6*n);

		lambda=r/l;
		sin2fi=sin(fi)*sin(fi);
		H=r*(1.0-cos(fi)+lambda/2.0*sin2fi+lambda*lambda*lambda/8*sin2fi*sin2fi);

		Ahfej=Adug=d*d*M_PI/4.0;
		V=terfogat();

		dmdm[0]=dmdm[1]=0.0;
		szA[0]=szA[1]=0.0;

		for(i=0; i<KOMPONENSEK; i++ )
		{  dmi[i]=0.0;
			qi[i]=q0i[i];
			Mi[i]=M0i[i];
			Rsi[i]=Re/Mi[i];
			cvi[i]=Rsi[i]/(kappa0i[i]-1.0);
			cpi[i]=kappa0i[i]*cvi[i];
		}
		cp=S_c(qi,cpi);
		cv=S_c(qi,cvi);
		Rs=cp-cv;
		kappa=cp/cv;
		T=Te0;
		p=p0;
		m=p*V/(T*Rs);
		U=cv*m*T;
		v=v0=V/m;
		M=Rs*m*T/(p*V);
		pk[SZIVO]=pk[KIPUFOGO]=p0;
		szv[SZIVO]=szv[KIPUFOGO]=v0;
		p_ism=p;
		T_ism=T;
		m_ism=m;

		for(i=0; i<KOMPONENSEK; i++ )
		  mi[i]=qi[i]*m;

		for( i=0; i<SZELEPEK; i++ )
		{	nyitva[i]=720.0-(nyit[i]-zar[i]);
			szK[i]=szD[i]*M_PI;
		}
		nyit[GYURUHORONY]=0.0;
		zar[GYURUHORONY]=720.0;
		szA[GYURUHORONY]=xi;

		qszheng=1/(1/alviz+dfalheng/lamheng+1/allev);
		qszhfej=1/(1/alviz+dfalhfej/lamhfej+1/allev);
		qszdug=1/(1/alviz+dfaldug/lamdug+1/allev);

		egesallapot=NINCS;
		VIBE2_Qp=m_adag*VIBE2_Pm;
		VIBE2_tp=eges_szoge*VIBE2_Pt;
		VIBE2_Qd=m_adag-VIBE2_Qp;
		VIBE2_td=eges_szoge;
		Minj=Mu=P=R=0.0;

	}
	
//	valtozas()	/* A gázjellemzők kiszámítása a három alapjellemző */
//	{				/* megváltozása alapján */
//		static int i;
//
//		U+=dU;
//		V+=dV;
//		m=0;
//		for(i=0; i<KOMPONENSEK; i++)
//		{	mi[i]+=dmi[i];
//			m+=mi[i];
//			dmi[i]=0;
//		}
//		for( i=0; i<KOMPONENSEK; i++ )
//			qi[i]=mi[i]/m ;
//		cp=S_c(qi,cpi);
//		cv=S_c(qi,cvi);
//		Rs=cp-cv;
//		kappa=cp/cv;
//		T=U/(cv*m);
//		p=Rs*T*m/V;
//		v=V/m;
//		ell=p*pow(v,kappa);
//		dV=dU=dm=0.0;
//		s=-cv*log(pow(Tabs0/T,kappa)*pow(pnorm/p,1-kappa));
//		S=m*s;
//		Aheng=d*M_PI*H;
//
//	}
	
	void valtozas()	/* A gázjellemzők kiszámítása a három alapjellemző */
	{				/* megváltozása alapján */
		int i;

		U+=dU;
		V+=dV;
		m=0;
		for(i=0; i<KOMPONENSEK; i++)
		{	mi[i]+=dmi[i];
			m+=mi[i];
			dmi[i]=0;
		}
		for( i=0; i<KOMPONENSEK; i++ )
			qi[i]=mi[i]/m ;
		cp=S_c(qi,cpi);
		cv=S_c(qi,cvi);
		Rs=cp-cv;
		kappa=cp/cv;
		T=U/(cv*m);
		p=Rs*T*m/V;
		v=V/m;
		ell=p*pow(v,kappa);
		dV=dU=dm=0.0;
		s=-cv*log(pow(Tabs0/T,kappa)*pow(pnorm/p,1-kappa));
		S=m*s;
		Aheng=d*M_PI*H;

	}
	
//	hofelszabadulas()
//	{
//		static valos pmert;
//
//		if( Meres && ((kie % outint)==0) )
//		{  fscanf(mert, "%s",SB );
//			pmert=atof(SB)*1e5+p_piezo;
//			dHTR=(cv*m*pmert/p*T-U)*dfok;
//			dU+=dHTR/dfok;
//		}
//		if( !Meres && ((kie % outint)==0) && Mod_Ablakok==1 )
//		{  if(fok>=320.0 && fok<=480.0)
//			{	fscanf(HTRin, "%s",SB );
//				dHTR=atof(SB);
//			}
//			fscanf(pin, "%s",SB );
//			pinmert=atof(SB);
//		}
//		valtozas();
//	}
	
	void hofelszabadulas()
	{
		double pmert;

		try {
			if( Meres > 0 && ((kie % outint)==0) )
			{	SB = mert.readLine();
				pmert=Double.parseDouble(SB)*1e5+p_piezo;
				dHTR=(cv*m*pmert/p*T-U)*dfok;
				dU+=dHTR/dfok;
			}
			if( Meres == 0 && ((kie % outint)==0) && Mod_Ablakok==1 )
			{  if(fok>=320.0 && fok<=480.0)
				{	SB = HTRin.readLine();
					dHTR=Double.parseDouble(SB);
				}
				SB = pin.readLine();
				pinmert=Double.parseDouble(SB);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		valtozas();
	}
	
//	folyamat()
//	{
//		if( fok>=stop && fok<stop+dfok )
//			if( strcmp(outname,"con")==0 )
//				printf("STOP\n"); /* Megállítás (Teszt) */
//		fi=fok/RAD;
//		sin2fi=sin(fi)*sin(fi);
//		H=r*(1.0-cos(fi)+lambda/2.0*sin2fi+lambda*lambda*lambda/8*sin2fi*sin2fi);
//		dm=dV=dU=dm_eg=0.0;
//
//		/* Adiabatikus kompresszió */
//		/* Változik: dV; dU=-p*dV */
//		dV_A=dV=terfogat()-V;
//		dU=U*(pow(V/(V+dV),kappa-1.0)-1.0);
//
//		if( !Mod_additiv )
//			valtozas();
//
//		/* Izochor hőcsere a falakon */
//		/* Változik: dU; dQ=dU+pdV; dV=0 -> dQ=dU */
//		if( Mod_ho )
//		{	hocsere();
//			if( !Mod_additiv )
//				valtozas();
//		}
//
//		/* Adiabatikus gázcsere a szelepeknél és a gyűrűillesztésnél*/
//		/* Változik: dmi; dU */
//		gazcsere();
//		if( !Mod_additiv )
//			valtozas();
//
//
//		/* Izochor égés */
//		/* Változik: dU; dmi; dQ=dU+pdV; dV=0 -> dQ=dU */
//		if( Mod_eges )
//			eges();
//
//		valtozas();
//
//		hofelszabadulas();
//	}
	
	/*
	 * TODO printf("STOP\n");
	 */
	void folyamat()
	{
//		if( fok>=stop && fok<stop+dfok )
//			if( strcmp(outname,"con")==0 )
//				printf("STOP\n"); /* Megállítás (Teszt) */
		fi=fok/RAD;
		sin2fi=sin(fi)*sin(fi);
		H=r*(1.0-cos(fi)+lambda/2.0*sin2fi+lambda*lambda*lambda/8*sin2fi*sin2fi);
		dm=dV=dU=dm_eg=0.0;

		/* Adiabatikus kompresszió */
		/* Változik: dV; dU=-p*dV */
		dV_A=dV=terfogat()-V;
		dU=U*(pow(V/(V+dV),kappa-1.0)-1.0);

		if( Mod_additiv == 0 )
			valtozas();

		/* Izochor hőcsere a falakon */
		/* Változik: dU; dQ=dU+pdV; dV=0 -> dQ=dU */
		if( Mod_ho > 0 )
		{	hocsere();
			if( Mod_additiv == 0 )
				valtozas();
		}

		/* Adiabatikus gázcsere a szelepeknél és a gyűrűillesztésnél*/
		/* Változik: dmi; dU */
		gazcsere();
		if( Mod_additiv == 0 )
			valtozas();


		/* Izochor égés */
		/* Változik: dU; dmi; dQ=dU+pdV; dV=0 -> dQ=dU */
		if( Mod_eges > 0 )
			eges();

		valtozas();

		hofelszabadulas();
	}
	
//	fgetline(file fgl, char* sor )
//	{
//		static int i;
//
//		i=0;
//		do
//		{	sor[i]=fgetc(fgl);
//	/*		printf( "%c %d\n", sor[i],sor[i] );
//		*/	i++;
//		}while( sor[i-1]!=10 && !feof(in) );
//		SB[i-1]=0;
//	}

//	char* vanegyenlo( char* SB )
//	{
//		static char* megvan;
//
//		megvan=strstr( SB, "=" );
//		if( megvan )
//			megvan++;
//		return( megvan );
//	}

//	valos josorker(char** adatnev)
//	{
//		static char* adatertek;
//
//		do
//		{	fgetline(in, SB );
//			adatertek=vanegyenlo(SB);
//		}while( !feof(in) && adatertek==NULL );
//		if( feof(in) )
//			adatertek=NULL;
//		*adatnev=SB;
//		while( *adatnev[0]=='\t' || *adatnev[0]==' ' )
//			(*adatnev)++;
//		if( !feof(in) )
//		{	*(strstr( SB, " " ))=0;
//			return( atof(adatertek) );
//		}
//		else
//			return( 0 );
//	}
	
	boolean josorker()
	{
		try {
			SB = in.readLine();
			if(SB==null)
				return false;
			SB = SB.trim();
			int egyenlo = SB.indexOf('=');
			if(egyenlo < 0)
				return true;
			double ertek = Double.parseDouble(SB.substring(egyenlo+1));
			String nev = SB.substring(0, egyenlo);
			int sp = nev.indexOf(' ');
			if(sp>0)
				nev = nev.substring(0,sp);
			sp = nev.indexOf('\t');
			if(sp>0)
				nev = nev.substring(0,sp);
			aST.put(nev, ertek);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	boolean josormap(Map<String, String> inputValues) {
		try {
			for(String name:inputValues.keySet()) {
				aST.put(name,  Double.parseDouble(inputValues.get(name)));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
	
//	valos adatkikeres( char* nev )
//	{
//		static int i,m;
//
//		m=FALSE;
//		for( i=0; i<aSmax && !m; i++ )
//			if( strcmp( nev, aST.nev[i] )==0 )
//				m=TRUE;
//		if( !m )
//			{ printf("Nincs megadva a %s paraméter!\n\g",nev); exit(4);}
//		return( aST.ertek[i-1] );
//	}

	Double adatkikeres( String nev )
	{
		if(!aST.containsKey(nev))
		{
			fprintf(out, "Nincs megadva a %s paraméter!\n",nev); System.exit(4);
		}
		
		return aST.get(nev);
	}
	
//	adatmodosit( char* nev, valos ertek)
//	{
//		static int i,m;
//
//		m=FALSE;
//		for( i=0; i<aSmax && !m; i++ )
//			if( strcmp( nev, aST.nev[i] )==0 )
//				m=TRUE;
//		if( !m && !feof(in))
//			{ printf("Nem volt megadva a %s paraméter!\n\g",nev); exit(4);}
//
//		aST.ertek[i-1]=ertek;
//	}

//	adatbeolvasasII(char* benev)
//	{
//		static int i,j;
//
//		if( benev==NULL )
//		{	if ((in=fopen(inname,"r"))==NULL)
//				{ printf("Nem tudom megnyitni a bemeneti file-t!\n\g"); exit(2);} /*abort*/
//		}
//		else
//			if ((in=fopen(benev,"r"))==NULL)
//				{ printf("Nem tudom megnyitni a bemeneti file-t!\n\g"); exit(2);} /*abort*/
//
//		i=0;
//		if( benev==NULL )	do
//		{
//			aSertek=josorker(&aSnev);
//			printf("%s = %g\n", aSnev, aSertek );
//			if( !feof(in) );
//			{  if( i==aSmax )
//				{ printf("Túl sok paraméter van megadva!\n\g"); exit(3);}
//				aST.nev[i]=strdup(aSnev);
//				aST.ertek[i]=aSertek;
//			}
//			i=i+1;
//		}
//		while( !feof(in) );
//
//		if( benev!=NULL )
//		{	do
//			{
//				aSertek=josorker(&aSnev);
//				printf("%s = %g\n", aSnev, aSertek );
//				if( !feof(in) );
//					adatmodosit( aSnev,aSertek );
//			}
//			while( !feof(in) );
//		}
//
//		p0=adatkikeres("p0")*1.00e5;
//		T0=adatkikeres("t0")+Tabs0;
//		n=adatkikeres("n");
//		legfel=adatkikeres("legfel");
//		pk[GYURUHORONY]=adatkikeres("pk[GYURUHORONY]")*1e5;
//		szv[GYURUHORONY]=adatkikeres("szv[GYURUHORONY]");
//		m_adag=adatkikeres("m_adag")*1e-6;
//		bef_kar=adatkikeres("be_kar")*1e-6;
//		Egesho=adatkikeres("Egesho")*1e6;
//		p_piezo=adatkikeres("p_piezo")*1e5;
//		Te0=adatkikeres("te0")+Tabs0;
//		szT[SZIVO]=szT[KIPUFOGO]=+Tabs0;
//		q0i[0]=adatkikeres("N//2")/100.0;
//		q0i[1]=adatkikeres("O//2")/100.0;
//		q0i[2]=adatkikeres("Ar")/100.0;
//		q0i[3]=adatkikeres("CO//2")/100.0;
//		q0i[4]=adatkikeres("H//2O")/100.0;
//		szT[SZIVO]=szT[KIPUFOGO]=T0;
//		dfok=adatkikeres("dfok");
//		hiba=adatkikeres("hiba")/100.0;
//		outint=adatkikeres("outint");
//		Vc=adatkikeres("Vc");
//		r=adatkikeres("r");
//		l=adatkikeres("l");
//		d=adatkikeres("d");
//		be_szog=adatkikeres("be_szog");
//		nyit[SZIVO]=adatkikeres("os");
//		zar[SZIVO]=adatkikeres("cs");
//		nyit[KIPUFOGO]=adatkikeres("ok");
//		zar[KIPUFOGO]=adatkikeres("ck");
//		szD[SZIVO]=adatkikeres("dsz");
//		szH[SZIVO]=adatkikeres("hsz");
//		szD[KIPUFOGO]=adatkikeres("dk");
//		szH[KIPUFOGO]=adatkikeres("hk");
//		szelepM=adatkikeres("szM");
//		szAlak[SZIVO]=adatkikeres("szA");
//		szAlak[KIPUFOGO]=adatkikeres("kA");
//		szCS[SZIVO]=adatkikeres("szcs");
//		szCS[KIPUFOGO]=adatkikeres("kcs");
//		xi=adatkikeres("xi");
//		lamheng=adatkikeres("lamheng");
//		lamhfej=adatkikeres("lamhfej");
//		lamdug=adatkikeres("lamdug");
//		alviz=adatkikeres("alviz");
//		allev=adatkikeres("allev");
//		dfalheng=adatkikeres("dfalheng");
//		dfalhfej=adatkikeres("dfalhfej");
//		dfaldug=adatkikeres("dfaldug");
//		Thk=adatkikeres("Thk")+Tabs0;
//		Mod_Ablakok=adatkikeres("Nagyság");
//		Mod_HTR=adatkikeres("Hőfelszabadulások");
//		Mod_additiv=adatkikeres("Mod_additiv");
//		Mod_ho=adatkikeres("Mod_ho");
//		Mod_karter=adatkikeres("Mod_karter");
//		Mod_eges=adatkikeres("Mod_eges");
//		GyulasModel=adatkikeres("GyulasModel");
//		/* GyulasModel=0; */
//		EgesModel=adatkikeres("EgesModell");
//		Meres=adatkikeres("Meres");
//
//		GYULLAD_SZOG=adatkikeres("GYULLAD_SZOG");
//
//		WOLFER_E0=adatkikeres("WOLFER_E0");
//		WOLFER_A1=adatkikeres("WOLFER_A1");
//		WOLFER_n=adatkikeres("WOLFER_n");
//
//		SCHMIDT_c=adatkikeres("SCHMIDT_c");
//		SCHMIDT_n=adatkikeres("SCHMIDT_n");
//		SCHMIDT_E0=adatkikeres("SCHMIDT_E0");
//
//		BAERT_t0=adatkikeres("BAERT_t0");
//		BAERT_A=adatkikeres("BAERT_A");
//		BAERT_B=adatkikeres("BAERT_B");
//		BAERT_n1=adatkikeres("BAERT_n1");
//		BAERT_C=adatkikeres("BAERT_C");
//		BAERT_n2=adatkikeres("BAERT_n2");
//
//		LISEVSZKIJ_A=adatkikeres("LISEVSZKIJ_A");
//		LISEVSZKIJ_C=adatkikeres("LISEVSZKIJ_C");  /*Cetánszám*/
//		LISEVSZKIJ_k=adatkikeres("LISEVSZKIJ_k");
//		LISEVSZKIJ_epsz=adatkikeres("LISEVSZKIJ_epsz"); /*Kompresszióviszony*/
//		LISEVSZKIJ_l=adatkikeres("LISEVSZKIJ_l");
//		LISEVSZKIJ_m=adatkikeres("LISEVSZKIJ_m");
//		LISEVSZKIJ_n=adatkikeres("LISEVSZKIJ_n");
//		LISEVSZKIJ_p=adatkikeres("LISEVSZKIJ_p");
//
//		KADOTA_z=adatkikeres("KADOTA_z");
//		KADOTA_p=adatkikeres("KADOTA_p");
//		KADOTA_fi=adatkikeres("KADOTA_fi");
//		KADOTA_E=adatkikeres("KADOTA_E");
//
//		eges_szoge=adatkikeres("egesszog");
//
//		VIBE_m=adatkikeres("VIBE_m");
//		VIBE_A=adatkikeres("VIBE_A");
//
//		VIBE2_Pm=adatkikeres("VIBE2_Pm");
//		VIBE2_Pt=adatkikeres("VIBE2_Pt");
//		VIBE2_Ap=adatkikeres("VIBE2_Ap");
//		VIBE2_mp=adatkikeres("VIBE2_mp");
//		VIBE2_Ad=adatkikeres("VIBE2_Ad");
//		VIBE2_md=adatkikeres("VIBE2_md");
//
//		WHITEHOUSE_K=adatkikeres("WHITEHOUSE_K");
//		WHITEHOUSE_x=adatkikeres("WHITEHOUSE_x");
//		WHITEHOUSE_m=adatkikeres("WHITEHOUSE_m");
//		WHITEHOUSE_Kr=adatkikeres("WHITEHOUSE_Kr");
//		WHITEHOUSE_act=adatkikeres("WHITEHOUSE_act");
//		WHITEHOUSE_a=adatkikeres("WHITEHOUSE_a");
//		WHITEHOUSE_C=adatkikeres("WHITEHOUSE_C");
//
//	}

	void adatbeolvasasII(Map<String, String> inputValues)
	{
		josormap(inputValues);

		p0=adatkikeres("p0")*1.00e5;
		T0=adatkikeres("t0")+Tabs0;
		n=adatkikeres("n");
		legfel=adatkikeres("legfel");
		pk[GYURUHORONY]=adatkikeres("pk[GYURUHORONY]")*1e5;
		szv[GYURUHORONY]=adatkikeres("szv[GYURUHORONY]");
		m_adag=adatkikeres("m_adag")*1e-6;
		bef_kar=adatkikeres("be_kar")*1e-6;
		Egesho=adatkikeres("Egesho")*1e6;
		p_piezo=adatkikeres("p_piezo")*1e5;
		Te0=adatkikeres("te0")+Tabs0;
		szT[SZIVO]=szT[KIPUFOGO]=+Tabs0;
		q0i[0]=adatkikeres("N//2")/100.0;
		q0i[1]=adatkikeres("O//2")/100.0;
		q0i[2]=adatkikeres("Ar")/100.0;
		q0i[3]=adatkikeres("CO//2")/100.0;
		q0i[4]=adatkikeres("H//2O")/100.0;
		szT[SZIVO]=szT[KIPUFOGO]=T0;
		dfok=adatkikeres("dfok");
		hiba=adatkikeres("hiba")/100.0;
		outint=adatkikeres("outint").intValue();
		Vc=adatkikeres("Vc");
		r=adatkikeres("r");
		l=adatkikeres("l");
		d=adatkikeres("d");
		be_szog=adatkikeres("be_szog");
		nyit[SZIVO]=adatkikeres("os");
		zar[SZIVO]=adatkikeres("cs");
		nyit[KIPUFOGO]=adatkikeres("ok");
		zar[KIPUFOGO]=adatkikeres("ck");
		szD[SZIVO]=adatkikeres("dsz");
		szH[SZIVO]=adatkikeres("hsz");
		szD[KIPUFOGO]=adatkikeres("dk");
		szH[KIPUFOGO]=adatkikeres("hk");
		szelepM=adatkikeres("szM");
		szAlak[SZIVO]=adatkikeres("szA");
		szAlak[KIPUFOGO]=adatkikeres("kA");
		szCS[SZIVO]=adatkikeres("szcs");
		szCS[KIPUFOGO]=adatkikeres("kcs");
		xi=adatkikeres("xi");
		lamheng=adatkikeres("lamheng");
		lamhfej=adatkikeres("lamhfej");
		lamdug=adatkikeres("lamdug");
		alviz=adatkikeres("alviz");
		allev=adatkikeres("allev");
		dfalheng=adatkikeres("dfalheng");
		dfalhfej=adatkikeres("dfalhfej");
		dfaldug=adatkikeres("dfaldug");
		Thk=adatkikeres("Thk")+Tabs0;
		Mod_Ablakok=adatkikeres("Nagyság").intValue();
		Mod_HTR=adatkikeres("Hőfelszabadulások").intValue();
		Mod_additiv=adatkikeres("Mod_additiv").intValue();
		Mod_ho=adatkikeres("Mod_ho").intValue();
		Mod_karter=adatkikeres("Mod_karter").intValue();
		Mod_eges=adatkikeres("Mod_eges").intValue();
		GyulasModel=adatkikeres("GyulasModel").intValue();
		/* GyulasModel=0; */
		EgesModel=adatkikeres("EgesModell").intValue();
		Meres=adatkikeres("Meres").intValue();

		GYULLAD_SZOG=adatkikeres("GYULLAD_SZOG");

		WOLFER_E0=adatkikeres("WOLFER_E0");
		WOLFER_A1=adatkikeres("WOLFER_A1");
		WOLFER_n=adatkikeres("WOLFER_n");

		SCHMIDT_c=adatkikeres("SCHMIDT_c");
		SCHMIDT_n=adatkikeres("SCHMIDT_n");
		SCHMIDT_E0=adatkikeres("SCHMIDT_E0");

		BAERT_t0=adatkikeres("BAERT_t0");
		BAERT_A=adatkikeres("BAERT_A");
		BAERT_B=adatkikeres("BAERT_B");
		BAERT_n1=adatkikeres("BAERT_n1");
		BAERT_C=adatkikeres("BAERT_C");
		BAERT_n2=adatkikeres("BAERT_n2");

		LISEVSZKIJ_A=adatkikeres("LISEVSZKIJ_A");
		LISEVSZKIJ_C=adatkikeres("LISEVSZKIJ_C");  /*Cetánszám*/
		LISEVSZKIJ_k=adatkikeres("LISEVSZKIJ_k");
		LISEVSZKIJ_epsz=adatkikeres("LISEVSZKIJ_epsz"); /*Kompresszióviszony*/
		LISEVSZKIJ_l=adatkikeres("LISEVSZKIJ_l");
		LISEVSZKIJ_m=adatkikeres("LISEVSZKIJ_m");
		LISEVSZKIJ_n=adatkikeres("LISEVSZKIJ_n");
		LISEVSZKIJ_p=adatkikeres("LISEVSZKIJ_p");

		KADOTA_z=adatkikeres("KADOTA_z");
		KADOTA_p=adatkikeres("KADOTA_p");
		KADOTA_fi=adatkikeres("KADOTA_fi");
		KADOTA_E=adatkikeres("KADOTA_E");

		eges_szoge=adatkikeres("egesszog");

		VIBE_m=adatkikeres("VIBE_m");
		VIBE_A=adatkikeres("VIBE_A");

		VIBE2_Pm=adatkikeres("VIBE2_Pm");
		VIBE2_Pt=adatkikeres("VIBE2_Pt");
		VIBE2_Ap=adatkikeres("VIBE2_Ap");
		VIBE2_mp=adatkikeres("VIBE2_mp");
		VIBE2_Ad=adatkikeres("VIBE2_Ad");
		VIBE2_md=adatkikeres("VIBE2_md");

		WHITEHOUSE_K=adatkikeres("WHITEHOUSE_K");
		WHITEHOUSE_x=adatkikeres("WHITEHOUSE_x");
		WHITEHOUSE_m=adatkikeres("WHITEHOUSE_m");
		WHITEHOUSE_Kr=adatkikeres("WHITEHOUSE_Kr");
		WHITEHOUSE_act=adatkikeres("WHITEHOUSE_act");
		WHITEHOUSE_a=adatkikeres("WHITEHOUSE_a");
		WHITEHOUSE_C=adatkikeres("WHITEHOUSE_C");

	}
	
//	adatbeolvasas() /* Kezdeti paraméterek beolvasása */
//	{
//		static int i;
//
//		if ((in=fopen(inname,"r"))==NULL) { printf("Nem tudom megnyitni a bemeneti file-t!\n\g"); exit(2);} /*abort*/
//
//		p0=valosbe(in)*1.00e5;
//		T0=valosbe(in)+Tabs0;
//		n=valosbe(in);
//		legfel=valosbe(in);
//		pk[GYURUHORONY]=valosbe(in)*1e5;
//		szv[GYURUHORONY]=valosbe(in);
//		m_adag=valosbe(in)*1e-6;
//		bef_kar=valosbe(in)*1e-6;
//		Egesho=valosbe(in)*1e6;
//		p_piezo=valosbe(in)*1e5;
//		Te0=valosbe(in)+Tabs0;
//		szT[SZIVO]=szT[KIPUFOGO]=valosbe(in)+Tabs0;
//		for( i=0; i<KOMPONENSEK; i++ )
//			q0i[i]=valosbe(in)/100.0 ;
//		szT[SZIVO]=szT[KIPUFOGO]=T0;
//		dfok=valosbe(in);
//		hiba=valosbe(in)/100.0;
//		outint=valosbe(in);
//		Vc=valosbe(in);
//		r=valosbe(in);
//		l=valosbe(in);
//		d=valosbe(in);
//		be_szog=valosbe(in);
//		nyit[SZIVO]=valosbe(in);
//		zar[SZIVO]=valosbe(in);
//		nyit[KIPUFOGO]=valosbe(in);
//		zar[KIPUFOGO]=valosbe(in);
//		szD[SZIVO]=valosbe(in);
//		szH[SZIVO]=valosbe(in);
//		szD[KIPUFOGO]=valosbe(in);
//		szH[KIPUFOGO]=valosbe(in);
//		szelepM=valosbe(in);
//		szAlak[SZIVO]=valosbe(in);
//		szAlak[KIPUFOGO]=valosbe(in);
//		szCS[SZIVO]=valosbe(in);
//		szCS[KIPUFOGO]=valosbe(in);
//		xi=valosbe(in);
//		lamheng=valosbe(in);
//		lamhfej=valosbe(in);
//		lamdug=valosbe(in);
//		alviz=valosbe(in);
//		allev=valosbe(in);
//		dfalheng=valosbe(in);
//		dfalhfej=valosbe(in);
//		dfaldug=valosbe(in);
//		Thk=valosbe(in)+Tabs0;
//		Mod_Ablakok=valosbe(in);
//		Mod_HTR=valosbe(in);
//		Mod_additiv=valosbe(in);
//		Mod_ho=valosbe(in);
//		Mod_karter=valosbe(in);
//		Mod_eges=valosbe(in);
//		GyulasModel=valosbe(in);
//		/* GyulasModel=0; */
//		EgesModel=valosbe(in);
//		Meres=valosbe(in);
//
//		GYULLAD_SZOG=valosbe(in);
//
//		WOLFER_E0=valosbe(in);
//		WOLFER_A1=valosbe(in);
//		WOLFER_n=valosbe(in);
//
//		SCHMIDT_c=valosbe(in);
//		SCHMIDT_n=valosbe(in);
//		SCHMIDT_E0=valosbe(in);
//
//		BAERT_t0=valosbe(in);
//		BAERT_A=valosbe(in);
//		BAERT_B=valosbe(in);
//		BAERT_n1=valosbe(in);
//		BAERT_C=valosbe(in);
//		BAERT_n2=valosbe(in);
//
//		LISEVSZKIJ_A=valosbe(in);
//		LISEVSZKIJ_C=valosbe(in);  /*Cetánszám*/
//		LISEVSZKIJ_k=valosbe(in);
//		LISEVSZKIJ_epsz=valosbe(in); /*Kompresszióviszony*/
//		LISEVSZKIJ_l=valosbe(in);
//		LISEVSZKIJ_m=valosbe(in);
//		LISEVSZKIJ_n=valosbe(in);
//		LISEVSZKIJ_p=valosbe(in);
//
//		KADOTA_z=valosbe(in);
//		KADOTA_p=valosbe(in);
//		KADOTA_fi=valosbe(in);
//		KADOTA_E=valosbe(in);
//
//		eges_szoge=valosbe(in);
//
//		VIBE_m=valosbe(in);
//		VIBE_A=valosbe(in);
//
//		VIBE2_Pm=valosbe(in);
//		VIBE2_Pt=valosbe(in);
//		VIBE2_Ap=valosbe(in);
//		VIBE2_mp=valosbe(in);
//		VIBE2_Ad=valosbe(in);
//		VIBE2_md=valosbe(in);
//
//		WHITEHOUSE_K=valosbe(in);
//		WHITEHOUSE_x=valosbe(in);
//		WHITEHOUSE_m=valosbe(in);
//		WHITEHOUSE_Kr=valosbe(in);
//		WHITEHOUSE_act=valosbe(in);
//		WHITEHOUSE_a=valosbe(in);
//		WHITEHOUSE_C=valosbe(in);
//	/***/
//	  fclose(in);
//	}
//
//	billentyu()	/* nyomkövetés, megszakítás, */
//	{				/* egy állapot teljes lementése, beolvasása */
//		static char c;
//
//		c=0;
//		if( trace )
//			c=getch();	/* Várakozás */
//		else if( kbhit() )
//			c=getch();
//		switch( c )
//		{  case 'q': exit(4); /* Vége a programnak */	break;
//		}
//	}
//
	void billentyu()
	{
		try {
			if(trace > 0)
			{
				int k = System.in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//
//	main(int argc,char * argv[])
//	{
//		VideoTest();
//
//		/* Adminisztráció */
//
//		if(argc<4) { printf("Nincs megadva a három paraméterfile !\n\g"); exit(1); } /*abort*/
//		strcpy(inname,argv[1]);
//		if(argc>2)
//			strcpy(outname,argv[2]);
//		else
//			strcpy(outname,"con");
//		if(argc>3)
//			strcpy(mertname,argv[3]);
//		trace=0;
//		if(argc>4)
//			trace=atoi(argv[4]);
//		stop=360;
//		if(argc>5)
//			stop=atof(argv[5]);
//		if( strcmp(outname,"con")==0 )
//			printf("Motorszimuláció 4.0\n");
//
//		/* Programkezdés */
//
//	/*	adatbeolvasas();*/
//		adatbeolvasasII(NULL);
//		strcpy(modositname,mertname);
//		for( moi=0; modositname[moi]!='.'; moi++ );
//		modositname[moi+1]='u';
//		modositname[moi+2]='z';
//		modositname[moi+3]='a';
//		modositname[moi+4]=0;
//
//		if((modin=fopen(modositname,"r"))!=NULL)
//		{	fclose(modin);
//			adatbeolvasasII(modositname);
//		}
//			
//		kezdoertekek();
//		if( Meres )
//		{	if((mert=fopen(mertname,"r"))==NULL) { printf("Nem tudom megnyitni a mert file-t!\n\g"); exit(4);} /*abort*/
//			strcpy(HTRname,mertname);
//			for( HTRni=0; HTRname[HTRni]!='.'; HTRni++ );
//			HTRname[HTRni+1]='h';
//			HTRname[HTRni+2]='t';
//			HTRname[HTRni+3]='r';
//			HTRname[HTRni+4]=0;
//		}
//		else
//		{	if((mert=fopen(mertname,"r"))==NULL) { printf("Nem tudom megnyitni a mert file-t!\n\g"); exit(4);} /*abort*/
//			strcpy(HTRname,mertname);
//			for( HTRni=0; HTRname[HTRni]!='.'; HTRni++ );
//			HTRname[HTRni+1]='h';
//			HTRname[HTRni+2]='t';
//			HTRname[HTRni+3]=EgesModel+'0';
//			HTRname[HTRni+4]=0;
//
//			strcpy(poutname,HTRname);
//			poutname[HTRni+1]='p';
//			poutname[HTRni+2]=EgesModel+'0';
//			poutname[HTRni+3]=0;
//
//			if( Mod_Ablakok==1 )
//				strcpy(HTRinname,HTRname);
//				HTRinname[HTRni+3]='r';
//				strcpy(pinname,HTRname);
//				pinname[HTRni+1]='p';
//				pinname[HTRni+2]='a';
//				pinname[HTRni+3]=0;
//		}
//
//		if( strcmp(outname,"con")!=0 )
//		{	VideoInit();
//			switch( Mod_Ablakok )
//			{  case 0:
//					Tolt2D(&kozep, VideoMaxX/2, VideoMaxY*.5 );
//					FTmin=0.0;
//					FTmax=720.0;
//					FToszt=90.0;
//					break;
//				case 1:
//					Tolt2D(&kozep, VideoMaxX/2, VideoMaxY*.8 );
//					FTmin=320.0;
//					FTmax=480.0;
//					FToszt=10.0;
//					break;
//			}
//			p_f=VPinit( 0,0, VideoMaxX,kozep.y, FTmin,0.0, FTmax,80.0, VideoColors[VideoMaxColor-1], 6 );
//			pp_f=VPinit( 0,0, VideoMaxX,kozep.y, FTmin,0.0, FTmax,80.0, VideoColors[VideoHalfColor-1], 6 );
//			VPtengely( p_f, FToszt, 10.0, "Főtengely fok [°]", "p  ", "[bar]" );
//			p_V=VPinit( 0,kozep.y, kozep.x,VideoMaxY, 0.0,0.0, 1.0,80.0, VideoColors[VideoHalfColor-2], 6 );
//			VPtengely( p_V, .2, 10.0, "V [liter]", "p  ", "[bar]" );
//			T_s=VPinit( kozep.x,kozep.y, VideoMaxX,VideoMaxY, 0.0,0.0, 1500.0, 2500.0, VideoColors[VideoHalfColor-3], 4 );
//			VPtengely( T_s, 250.0, 500.0, "s [J/kg·K]", "T ", "[°C]" );
//
//	      VPpont(p_f, fok, p*1e-5 );
//			VPpont(p_V, V*1e3, p*1e-5 );
//			VPpont(T_s, s, T-Tabs0 );
//
//			p_HTR=VPinit( 0,0, VideoMaxX,kozep.y, FTmin,0.0, FTmax,80.0, VideoColors[VideoMaxColor-4], 6 );
//			HTR_f=VPinit( 0,0, VideoMaxX,kozep.y, FTmin,0.0, FTmax,80.0, VideoColors[VideoHalfColor-4], 6 );
//			p_Mu=VPinit( 0,0, VideoMaxX,kozep.y, FTmin,0.0, FTmax,80.0, VideoColors[VideoHalfColor-5], 6 );
//			p_P=VPinit( 0,0, VideoMaxX,kozep.y, FTmin,0.0, FTmax,80.0, VideoColors[VideoHalfColor-2], 6 );
//			p_R=VPinit( 0,0, VideoMaxX,kozep.y, FTmin,0.0, FTmax,80.0, VideoColors[VideoHalfColor-3], 6 );
//			p_T=VPinit( 0,0, VideoMaxX,kozep.y, FTmin,0.0, FTmax,2500.0, VideoColors[VideoMaxColor-2], 6 );
//			if( Mod_HTR )
//				VPtengely( p_T, FToszt, 1000.0, "Főtengely fok [°]", "p  ", "[bar]" );
//		}
//
//		/* A ciklus */
//
//		do {
//			if ((out=fopen(outname,"w"))==NULL) { printf("Nem tudom megnyitni a kimeneti file-t!\n\g"); exit(3);} /*abort*/
//			HTRout=fopen(HTRname,"w");
//			if( !Meres ) pout=fopen(poutname,"w");
//			if( Mod_Ablakok==1 )
//			{	if((HTRin=fopen(HTRinname,"r"))==NULL) { printf("Nem tudom megnyitni a HTRin file-t!\n\g"); exit(4);} /*abort*/
//				if((pin=fopen(pinname,"r"))==NULL) { printf("Nem tudom megnyitni a pin file-t!\n\g"); exit(4);} /*abort*/
//			}
//			while( fok<720.0 )
//			{	folyamat();	/* Egy ciklus teljes számítása */
//				if( (kie++ % outint)==0 )
//				{  if( strcmp(outname,"con")==0 )
//					clrscr();
//					fprintf(out, "%g\t%g %g %g %g %g %g %g %g %g %g %g\n", fok,p,V,T-Tabs0,m,U,szA[0],szA[1],s,dHTR,dmdm[2],dm_eg*Egesho/dfok );
//					if(fok>=320.0 && fok<=480.0)
//					{	if( Meres )
//							fprintf(HTRout, "%g\n",dHTR/dfok );
//						else
//						{	fprintf(HTRout, "%g\n",dm_eg*Egesho/dfok );
//						}
//					}
//					if( !Meres ) fprintf(pout,"%g\n",p*1e-5);
//				}
//				if( strcmp(outname,"con")!=0 )
//				{  if( fok==0.0 )
//					{	VPpont(p_f, fok, p*1e-5 );
//						VPpont(p_V, V*1e3, p*1e-5 );
//						VPpont(T_s, s, T-Tabs0 );
//					}
//					else
//					{	VPpont(p_f, fok, p*1e-5 ); /*Ig*/
//						VPpont(p_V, V*1e3, p*1e-5 );
//						VPpont(T_s, s, T-Tabs0 );
//					}
//					if( Mod_HTR )
//					{
//						VPpont(p_HTR, fok, dm_eg*Egesho/dfok );
//						if( fok>=320.0 && fok<=480.0 )
//							if( Meres )
//								VPpont(p_HTR, fok, dHTR/dfok );
//							else if( !Meres && /* ((kie % outint)==0) && */ Mod_Ablakok==1 )
//							{	VPpont(HTR_f, fok, dHTR );
//							}
//						if( !Meres && /* ((kie % outint)==0) && */ Mod_Ablakok==1 )
//							VPpont(pp_f, fok, pinmert);
//						VPpont(p_Mu, fok, Mu*1e6 );
//						VPpont(p_P, fok, P*1e6 );
//						VPpont(p_R, fok, R*1e6 );
//						VPpont(p_T, fok, T );
//					}
//				}
//				fok+=dfok;
//				billentyu();
//			}
//			fok=0;
//			egesallapot=NINCS;
//			WHITEHOUSE_dR=0.0;
//			WHITEHOUSE_dP=0.0;
//			Minj=Mu=P=R=0.0;
//
//			if( !Meres )
//				fclose(pout);
//			if( !Meres )
//				fclose(HTRout);
//			if( strcmp(outname,"con")!=0 )
//				fclose(out);
//			if( ismetles==1 )
//				ismetles=2;
//			else
//			{	ismetles=1;
//				if( p_ism>=p*(1+hiba) OR p_ism<=p*(1-hiba) )
//					ismetles=0;
//				if( T_ism>=p*(1+hiba) OR T_ism<=T*(1-hiba) )
//					ismetles=0;
//				if( m_ism>=p*(1+hiba) OR m_ism<=m*(1-hiba) )
//					ismetles=0;
//				p_ism=p;
//				T_ism=T;
//				m_ism=m;
//			}
//			if( ismetles==1 )
//			{	p_f->szin=VideoColors[VideoMaxColor-1];
//				p_V->szin=VideoColors[VideoMaxColor-2];
//				T_s->szin=VideoColors[VideoMaxColor-3];
//			}
//			if( Meres )	ismetles=2;
//			if( Mod_Ablakok==1 )
//			{	fclose(HTRin);
//				fclose(pin);
//			}
//
//		} while( ismetles<2 );
//
//		/* A ciklus vége */
//
//		if( Meres )
//		{	fclose(mert);
//			fclose(HTRout);
//		}
//		fclose(out);
//		if( strcmp(outname,"con")!=0 )
//		{  varakoz();
//			VPclose(p_f);
//			VPclose(p_V);
//			VPclose(T_s);
//			VideoClose();
//		}
//	}

	/* 
	 *             argv[1]   argv[2]    argv[3] argv[4]    argv[5]
	 * motor42.exe motor4.sf motor4.out ?m?.pa  trace:0/1  stop:0/1
	 */
	
	public void main(Map<String, String> inputValues, List<MotorStep> outputValues, List<Double> mertValues, PrintWriter stdout)
	{
		Calendar cal = Calendar.getInstance();
		long TimeMillis = cal.getTimeInMillis();
		long TimeMillisMax = TimeMillis + 60*1000;
		
		out = stdout;
		
		// VideoTest();

		/* Adminisztráció */

		trace=0;

		stop=360;

		
		/* Programkezdés */

		/*	adatbeolvasas();*/
		
		
		adatbeolvasasII(inputValues);
		
		kezdoertekek();

		/* A ciklus */

		do {

			while (fok < 720.0 && cal.getTimeInMillis() < TimeMillisMax) {
				folyamat(); /* Egy ciklus teljes számítása */
				if ((kie++ % outint) == 0) {
					MotorStep motorStep = new MotorStep();
					motorStep.fok = fok;
					motorStep.p = p;
					motorStep.V = V;
					motorStep.T_Tabs0 = T - Tabs0;
					motorStep.m = m;
					motorStep.U = U;
					motorStep.szA_0 = szA[0];
					motorStep.szA_1 = szA[1];
					motorStep.s = s;
					motorStep.dHTR = dHTR;
					motorStep.dmdm_2 = dmdm[2];
					motorStep.dE_eges_dfok = dm_eg * Egesho / dfok;
					if (fok >= 320.0 && fok <= 480.0) {
						if (Meres > 0)
							mertValues.add(dHTR / dfok);
						else {
							mertValues.add(dm_eg * Egesho / dfok);
						}
					}				
					motorStep.p_bar = p * 1e-5;
					outputValues.add(motorStep);
				}
				fok += dfok;
				billentyu();
			}
			fok = 0;
			egesallapot = NINCS;
			WHITEHOUSE_dR = 0.0;
			WHITEHOUSE_dP = 0.0;
			Minj = Mu = P = R = 0.0;

			if (ismetles == 1)
				ismetles = 2;
			else {
				ismetles = 1;
				if (p_ism >= p * (1 + hiba) || p_ism <= p * (1 - hiba))
					ismetles = 0;
				if (T_ism >= p * (1 + hiba) || T_ism <= T * (1 - hiba))
					ismetles = 0;
				if (m_ism >= p * (1 + hiba) || m_ism <= m * (1 - hiba))
					ismetles = 0;
				p_ism = p;
				T_ism = T;
				m_ism = m;
			}
		} while (ismetles < 2 && cal.getTimeInMillis() < TimeMillisMax);

		TimeMillis = cal.getTimeInMillis() - TimeMillis;
		fprintf(out,"Calculation time: %s [msec]\n", String.valueOf(TimeMillis));
		
	}
	
	public Motor46()
	{
	}
	
	public double exp(double x)
	{
		return Math.exp(x);
	}
	
	public double pow(double x, double n)
	{
		return Math.pow(x, n);
	}
	
	public double sqrt(double x)
	{
		return Math.sqrt(x);
	}
	
	public double log(double x)
	{
		return Math.log(x);
	}

	public double sin(double x)
	{
		return Math.sin(x);
	}
	
	public double cos(double x)
	{
		return Math.cos(x);
	}
	
	public double max(double a, double b)
	{
		return Math.max(a, b);
	}

	public double min(double a, double b)
	{
		return Math.min(a, b);
	}
	
	public void fprintf(PrintWriter out, String text)
	{
		out.printf("%s", text);
	}
	
	public void fprintf(PrintWriter out, String format, String text)
	{
		out.printf(format, text);
	}	
}
