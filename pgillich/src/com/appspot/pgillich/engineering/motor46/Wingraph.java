package com.appspot.pgillich.engineering.motor46;

import java.lang.Math;

public class Wingraph 
{
//	#ifndef PI
//		#define PI 3.14159265358979323846
//	#endif
//	#ifndef RAD
//		#define RAD 57.2957795130823
//	#endif
//	#ifndef VELL
//		#define VELL 1e20
//	#endif
//	
//	#define Tolt2D( sp, xc, yc ) { ((sp)->x)=xc; ((sp)->y)=yc; }
//	#define Szabad( p )     free( p )
//	#define Foglal( p )   	{	p=malloc( sizeof(*p) ); }

	final double PI = Math.PI;
	final double RAD = Math.toDegrees(1.0);
	
	
//	VideoTest()
//	{
//		int xasp, yasp;			/* Used to read the aspect ratio*/
//		int GraphDriver;		/* The Graphics device driver		*/
//		int GraphMode;		/* The Graphics mode value		*/
//		int i;
//
//		GraphDriver = DETECT; 		/* Request auto-detection	*/
//		initgraph( &GraphDriver, &GraphMode, "" );
//		VideoMaxColor = getmaxcolor() ;	/* Read maximum number of colors*/
//		VideoMaxX = getmaxx();
//		VideoMaxY = getmaxy();			/* Read size of screen		*/
//		getaspectratio( &xasp, &yasp );	/* read the hardware aspect	*/
//		VideoTorzitas= (valos)xasp / (valos)yasp; /* Get correction factor	*/
//		VideoTextHeight=textheight( "H" );
//		VideoTextWidth=textwidth( "W" );
//		for( i=0; i<=VideoMaxColor; i++ )
//			VideoColors[i]=i;
//		for( ;i<16 ; i++ )
//			VideoColors[i]=VideoMaxColor;
//		VideoHalfColor=7;
//		closegraph();
//	}
	
	public void VideoTest()
	{
		
	}
	
//	VideoInit()
//	{
//		if( VideoMaxX == 0 )
//			kilep( "Nem tudok grafikus uzemmodba valtani" );
//		else
//		{
//			int GraphDriver;		/* The Graphics device driver		*/
//			int GraphMode;		/* The Graphics mode value		*/
//
//			GraphDriver = DETECT; 		/* Request auto-detection	*/
//			initgraph( &GraphDriver, &GraphMode, "" );
//			VideoFullPort = VPinit( 0,0, VideoMaxX,VideoMaxY, 0.0,0.0, 1.0,1.0, VideoMaxColor);
//		}
//	}
	
	public void VideoInit()
	{
		
	}
}
