/*
 * Copyright 2015 Loic Merckel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package lh.api.showcase.shared;

import java.util.HashSet;
import java.util.Set;

public enum CityCodeSZ {

	SAA,SAB,SAC,SAD,SAE,SAF,SAG,SAH,SAI,SAJ,SAK,SAL,SAM,SAN,SAO,SAP,SAQ,SAR,SAS,SAT,SAU,SAV,SAX,SAY,SAZ,SBA,SBB,SBC,SBE,SBF,SBG,SBH,
	SBI,SBJ,SBK,SBL,SBM,SBN,SBO,SBQ,SBR,SBS,SBT,SBU,SBV,SBW,SBX,SBY,SBZ,SCA,SCB,SCC,SCD,SCE,SCG,SCH,SCI,SCJ,SCK,SCL,SCM,SCN,SCO,SCP,SCQ,SCT,SCU,SCV,SCW,SCX,SCY,SCZ,SDB,SDC,SDD,SDE,SDF,SDG,SDH,SDI,SDJ,SDK,SDL,SDN,SDO,SDP,SDQ,SDR,SDS,SDT,SDW,SDX,SDY,SDZ,SEA,SEB,SEC,SED,SEF,SEG,SEH,SEI,SEJ,SEK,SEL,SEN,SEO,SEP,SEQ,SER,SES,SET,SEU,SEV,SEW,SEX,SEY,SEZ,SFA,SFC,SFD,SFE,SFG,SFH,SFI,SFJ,SFK,SFL,SFM,SFN,SFO,SFP,
	SFQ,SFR,SFS,SFT,SFU,SFV,SFW,SFX,SFZ,SGA,SGB,SGC,SGD,SGE,SGF,SGG,SGH,SGI,SGJ,SGK,SGM,SGN,SGO,SGP,SGQ,SGS,SGT,SGU,SGV,SGW,SGX,SGY,SGZ,SHA,SHB,SHC,SHD,SHE,SHF,SHG,SHH,SHI,SHJ,SHK,SHL,SHM,SHN,SHO,SHP,SHQ,SHR,SHS,SHT,SHU,SHV,SHW,SHX,SHY,SHZ,SIA,SIB,SIC,SID,SIE,SIF,SIH,SII,SIJ,SIK,SIL,SIM,SIN,SIO,SIP,SIQ,SIR,SIS,SIT,SIU,SIV,SIW,SIX,SIY,SIZ,SJA,SJB,SJC,SJD,SJE,SJF,SJG,SJH,SJI,SJJ,SJK,SJL,SJM,SJN,SJO,SJP,
	SJQ,SJR,SJS,SJT,SJU,SJV,SJW,SJX,SJY,SJZ,SKB,SKC,SKD,SKE,SKG,SKH,SKI,SKJ,SKK,SKL,SKM,SKN,SKO,SKP,SKQ,SKR,SKS,SKT,SKU,SKV,SKW,SKX,SKY,SKZ,SLA,SLB,SLC,SLD,SLE,SLF,SLG,SLH,SLI,SLK,SLL,SLM,SLN,SLO,SLP,SLQ,SLR,SLS,SLT,SLU,SLV,SLW,SLX,SLY,SLZ,SMA,SMB,SMC,SME,SMG,SMH,SMI,SMJ,SMK,SML,SMM,SMN,SMO,SMP,SMQ,SMR,SMS,SMT,SMU,SMV,SMW,SMX,SMY,SMZ,SNA,SNB,SNC,SND,SNE,SNF,SNG,SNH,SNI,SNJ,SNK,SNL,SNM,SNN,SNO,SNP,SNQ,
	SNR,SNS,SNT,SNU,SNV,SNW,SNX,SNY,SNZ,SOA,SOB,SOC,SOD,SOE,SOF,SOG,SOH,SOI,SOJ,SOK,SOL,SOM,SON,SOO,SOP,SOQ,SOR,SOT,SOU,SOV,SOW,SOX,SOY,SOZ,SPA,SPC,SPD,SPE,SPF,SPH,SPI,SPJ,SPK,SPM,SPN,SPO,SPP,SPQ,SPR,SPS,SPT,SPU,SPV,SPW,SPY,SPZ,SQA,SQB,SQC,SQD,SQE,SQF,SQG,SQH,SQI,SQJ,SQK,SQL,SQM,SQN,SQO,SQP,SQQ,SQR,SQV,SQZ,SRA,SRB,SRC,SRD,SRE,SRF,SRG,SRH,SRI,SRJ,SRK,SRL,SRM,SRN,SRO,SRP,SRQ,SRR,SRS,SRT,SRU,SRV,SRW,SRX,
	SRY,SRZ,SSA,SSC,SSD,SSE,SSG,SSH,SSI,SSK,SSL,SSM,SSO,SSP,SSQ,SSR,SSS,SST,SSU,SSV,SSW,SSY,SSZ,STA,STB,STC,STD,STE,STF,STG,STH,STI,STJ,STK,STL,STM,STO,STQ,STR,STS,STT,STU,STV,STW,STX,STY,STZ,SUA,SUB,SUC,SUD,SUE,SUF,SUG,SUH,SUI,SUJ,SUK,SUL,SUN,SUO,SUP,SUQ,SUR,SUT,SUU,SUV,SUW,SUX,SUY,SUZ,SVA,SVB,SVC,SVD,SVE,SVF,SVG,SVH,SVI,SVJ,SVK,SVL,SVM,SVP,SVQ,SVR,SVS,SVT,SVU,SVV,SVW,SVX,SVY,SVZ,SWA,SWB,SWC,SWD,SWE,
	SWF,SWG,SWH,SWI,SWJ,SWL,SWM,SWN,SWO,SWP,SWQ,SWR,SWS,SWT,SWU,SWV,SWW,SWX,SWY,SXA,SXB,SXD,SXE,SXG,SXH,SXI,SXJ,SXK,SXL,SXM,SXN,SXO,SXP,SXQ,SXR,SXS,SXT,SXU,SXV,SXW,SXX,SXY,SXZ,SYA,SYB,SYC,SYD,SYE,SYF,SYH,SYI,SYK,SYL,SYM,SYN,SYO,SYP,SYR,SYS,SYT,SYU,SYV,SYX,SYY,SYZ,SZA,SZC,SZD,SZF,SZG,SZH,SZK,SZL,SZO,SZP,SZQ,SZR,SZS,SZU,SZV,SZW,SZX,SZZ,TAA,TAB,TAC,TAD,TAE,TAG,TAH,TAI,TAK,TAL,TAM,TAN,TAO,TAP,TAQ,TAR,TAS,
	TAT,TAU,TAV,TAW,TAX,TAY,TAZ,TBA,TBB,TBC,TBD,TBE,TBF,TBG,TBH,TBI,TBJ,TBK,TBL,TBM,TBO,TBP,TBQ,TBR,TBS,TBT,TBU,TBV,TBW,TBX,TBY,TBZ,TCA,TCB,TCC,TCD,TCE,TCF,TCG,TCH,TCI,TCL,TCN,TCO,TCQ,TCS,TCT,TCU,TCW,TDA,TDB,TDD,TDG,TDJ,TDK,TDL,TDO,TDR,TDT,TDV,TDX,TEA,TEB,TEC,TED,TEE,TEF,TEG,TEH,TEI,TEK,TEL,TEM,TEN,TEO,TEP,TER,TES,TET,TEU,TEX,TEY,TEZ,TFA,TFF,TFI,TFL,TFM,TFT,TFY,TGB,TGD,TGE,TGF,TGG,TGH,TGI,TGJ,TGL,TGM,
	TGN,TGO,TGR,TGS,TGT,TGU,TGV,TGX,TGZ,THA,THB,THC,THE,THG,THH,THI,THK,THL,THM,THN,THO,THP,THR,THS,THT,THU,THV,THY,THZ,TIA,TIB,TIC,TID,TIE,TIF,TIG,TIH,TII,TIJ,TIL,TIM,TIN,TIO,TIP,TIQ,TIR,TIS,TIU,TIV,TIW,TIX,TIY,TIZ,TJA,TJB,TJG,TJH,TJI,TJM,TJQ,TJS,TJV,TKA,TKB,TKC,TKD,TKE,TKF,TKG,TKH,TKI,TKJ,TKK,TKL,TKM,TKN,TKO,TKP,TKQ,TKR,TKS,TKT,TKU,TKV,TKW,TKX,TKY,TKZ,TLA,TLB,TLC,TLD,TLE,TLF,TLG,TLH,TLI,TLJ,TLK,TLL,
	TLM,TLN,TLO,TLP,TLR,TLS,TLT,TLU,TLV,TLW,TLX,TLZ,TMA,TMC,TMD,TME,TMG,TMH,TMI,TMK,TML,TMM,TMN,TMO,TMP,TMQ,TMR,TMS,TMT,TMU,TMW,TMX,TMY,TMZ,TNA,TNB,TNC,TND,TNE,TNF,TNG,TNH,TNI,TNJ,TNK,TNL,TNM,TNN,TNO,TNP,TNQ,TNR,TNS,TNU,TNV,TNX,TOA,TOB,TOC,TOD,TOE,TOF,TOG,TOH,TOI,TOK,TOL,TOM,TON,TOP,TOQ,TOR,TOS,TOT,TOU,TOV,TOW,TOX,TOY,TOZ,TPA,TPC,TPE,TPG,TPH,TPI,TPJ,TPK,TPL,TPN,TPO,TPP,TPQ,TPR,TPS,TPT,TPU,TQN,TQS,TRA,
	TRB,TRC,TRD,TRE,TRF,TRG,TRH,TRI,TRJ,TRK,TRL,TRM,TRN,TRO,TRP,TRQ,TRR,TRS,TRT,TRU,TRV,TRW,TRX,TRY,TRZ,TSB,TSC,TSD,TSE,TSG,TSH,TSI,TSJ,TSK,TSL,TSM,TSN,TSP,TSR,TST,TSU,TSV,TSW,TSX,TSY,TSZ,TTA,TTB,TTC,TTD,TTE,TTG,TTH,TTI,TTJ,TTK,TTL,TTM,TTN,TTO,TTQ,TTR,TTS,TTT,TTU,TUA,TUB,TUC,TUD,TUE,TUF,TUG,TUI,TUJ,TUK,TUL,TUM,TUN,TUO,TUP,TUQ,TUR,TUS,TUT,TUU,TUV,TUW,TUX,TUY,TUZ,TVA,TVC,TVF,TVI,TVL,TVU,TVY,TWA,TWB,TWD,
	TWE,TWF,TWN,TWP,TWT,TWU,TWY,TXG,TXK,TXM,TXN,TXR,TXU,TYA,TYB,TYD,TYE,TYF,TYG,TYL,TYM,TYN,TYO,TYP,TYR,TYS,TYT,TYZ,TZM,TZN,TZX,UAC,UAE,UAH,UAI,UAK,UAL,UAP,UAQ,UAS,UAX,UBA,UBB,UBI,UBJ,UBP,UBR,UBS,UBT,UBU,UCA,UCC,UCE,UCK,UCN,UCT,UCY,UCZ,UDA,UDE,UDI,UDJ,UDN,UDO,UDR,UEE,UEL,UEO,UES,UET,UFA,UGA,UGC,UGI,UGN,UGO,UGS,UGU,UHE,UHF,UIB,UIH,UII,UIK,UIL,UIN,UIO,UIP,UIQ,UIR,UIT,UIZ,UJE,UKB,UKI,UKK,UKN,UKR,UKT,UKU,
	UKX,UKY,ULA,ULB,ULD,ULE,ULG,ULI,ULL,ULM,ULN,ULO,ULP,ULQ,ULS,ULU,ULX,ULY,ULZ,UMA,UMB,UMC,UMD,UME,UMI,UMM,UMR,UMT,UMU,UMY,UNA,UNC,UND,UNE,UNG,UNI,UNK,UNN,UNR,UNS,UNT,UNU,UOL,UON,UOS,UOX,UPA,UPC,UPF,UPG,UPL,UPN,UPP,UPR,UPV,UQE,URA,URB,URC,URE,URG,URI,URJ,URM,URN,URO,URR,URS,URT,URY,URZ,USH,USI,USK,USL,USM,USN,USO,USS,UST,USU,UTA,UTB,UTC,UTD,UTE,UTG,UTH,UTI,UTK,UTL,UTN,UTO,UTP,UTR,UTT,UTU,UTW,UUA,UUD,
	UUK,UUN,UUS,UUU,UVA,UVE,UVL,UVO,UWA,UWE,UYL,UYN,UZH,UZU,VAA,VAB,VAC,VAF,VAG,VAH,VAI,VAK,VAL,VAN,VAO,VAP,VAR,VAS,VAT,VAU,VAV,VAW,VAZ,VBS,VBV,VBY,VCA,VCB,VCC,VCD,VCE,VCF,VCH,VCR,VCT,VCV,VDA,VDB,VDC,VDE,VDI,VDM,VDP,VDR,VDS,VDZ,VEE,VEG,VEJ,VEL,VER,VEV,VEX,VEY,VFA,VGA,VGD,VGG,VGO,VGS,VGZ,VHC,VHM,VHN,VHY,VHZ,VIB,VIC,VID,VIE,VIG,VIH,VII,VIJ,VIK,VIL,VIN,VIQ,VIS,VIT,VIU,VIV,VIX,VJB,VJI,VJQ,VKG,VKS,VKT,VKW,
	VLA,VLC,VLD,VLE,VLG,VLI,VLK,VLL,VLM,VLN,VLO,VLP,VLR,VLS,VLU,VLV,VME,VMU,VNA,VNC,VNE,VNG,VNO,VNR,VNS,VNX,VOG,VOH,VOI,VOK,VOL,VOT,VOZ,VPE,VPN,VPS,VPY,VPZ,VQS,VRA,VRB,VRC,VRE,VRK,VRL,VRN,VRS,VRU,VRY,VSA,VSE,VSF,VSG,VSO,VST,VTA,VTB,VTE,VTF,VTG,VTL,VTN,VTU,VTZ,VUP,VUS,VVB,VVC,VVK,VVO,VVZ,VXC,VXE,VXO,VYD,VYS,WAA,WAB,WAC,WAD,WAE,WAF,WAG,WAH,WAI,WAJ,WAK,WAL,WAM,WAN,WAO,WAP,WAQ,WAR,WAS,WAT,WAU,WAV,WAW,WAY,
	WAZ,WBB,WBD,WBE,WBM,WBN,WBO,WBQ,WBR,WBU,WCA,WCH,WCR,WDA,WDB,WDG,WDH,WDI,WDN,WDR,WEA,WED,WEF,WEH,WEI,WEL,WEM,WEP,WES,WET,WEW,WEX,WFI,WFK,WGA,WGB,WGC,WGE,WGN,WGO,WGP,WGT,WGU,WGY,WHD,WHF,WHK,WHL,WHO,WHS,WHT,WHU,WIC,WID,WIK,WIN,WIO,WIR,WIT,WIU,WJA,WJF,WJR,WKA,WKB,WKI,WKJ,WKK,WKL,WKN,WKR,WLA,WLB,WLC,WLD,WLG,WLH,WLK,WLL,WLM,WLN,WLO,WLR,WLS,WLW,WMA,WMB,WMC,WMD,WME,WMH,WMK,WML,WMN,WMO,WMR,WMV,WMX,WNA,WNC,
	WND,WNE,WNN,WNP,WNR,WNS,WNU,WNZ,WOA,WOB,WOD,WOE,WOG,WOI,WOK,WOL,WON,WOO,WOT,WOW,WPA,WPB,WPC,WPK,WPL,WPM,WPO,WPR,WPU,WRA,WRE,WRG,WRH,WRI,WRL,WRO,WRW,WRY,WSA,WSB,WSD,WSF,WSG,WSH,WSJ,WSM,WSN,WSO,WSP,WSR,WST,WSU,WSX,WSY,WSZ,WTA,WTD,WTE,WTK,WTL,WTN,WTO,WTP,WTR,WTS,WTT,WTZ,WUA,WUD,WUG,WUH,WUN,WUS,WUU,WUV,WUX,WUZ,WVB,WVI,WVK,WVL,WVN,WWA,WWD,WWI,WWK,WWP,WWR,WWS,WWT,WWY,WXF,WXN,WYA,WYB,WYE,WYN,WYS,XAL,XAP,
	XAR,XAU,XAY,XBB,XBE,XBG,XBJ,XBL,XBN,XBO,XBR,XBW,XCH,XCL,XCM,XCN,XCO,XDE,XDJ,XEN,XES,XFN,XFW,XGA,XGG,XGL,XGN,XGR,XIC,XIE,XIG,XIL,XIN,XKA,XKH,XKO,XKS,XKY,XLB,XLF,XLJ,XLM,XLO,XLS,XLU,XLW,XMA,XMB,XMC,XMD,XMG,XMH,XMI,XML,XMN,XMP,XMS,XMY,XNG,XNN,XNT,XNU,XPA,XPK,XPL,XPP,XPR,XPU,XQP,XQU,XRR,XRY,XSC,XSE,XSH,XSI,XSM,XSO,XTG,XTL,XTO,XTR,XUZ,XVL,XXB,XXP,XYA,XYE,XYR,XZA,YAA,YAB,YAC,YAD,YAE,YAF,YAG,YAI,YAJ,YAK,
	YAL,YAM,YAN,YAO,YAP,YAQ,YAS,YAT,YAV,YAX,YAY,YAZ,YBA,YBC,YBD,YBE,YBF,YBG,YBH,YBI,YBJ,YBK,YBL,YBM,YBN,YBO,YBP,YBQ,YBR,YBT,YBV,YBW,YBX,YBY,YCA,YCB,YCC,YCD,YCE,YCF,YCG,YCH,YCI,YCJ,YCK,YCL,YCM,YCN,YCO,YCP,YCQ,YCR,YCS,YCT,YCU,YCV,YCW,YCX,YCY,YCZ,YDA,YDB,YDC,YDE,YDF,YDG,YDH,YDI,YDK,YDL,YDN,YDO,YDP,YDQ,YDR,YDS,YDV,YDX,YEA,YEC,YEI,YEK,YEL,YEM,YEN,YEO,YEP,YEQ,YER,YET,YEU,YEV,YEY,YFA,YFB,YFC,YFE,YFG,YFH,YFL,
	YFO,YFR,YFS,YFX,YGA,YGB,YGC,YGE,YGG,YGH,YGJ,YGK,YGL,YGM,YGN,YGO,YGP,YGQ,YGR,YGS,YGT,YGV,YGW,YGX,YGY,YGZ,YHA,YHB,YHC,YHD,YHE,YHF,YHG,YHI,YHK,YHM,YHN,YHO,YHP,YHR,YHS,YHT,YHY,YHZ,YIB,YIC,YIF,YIG,YIH,YIK,YIN,YIO,YIV,YIW,YIX,YJA,YJF,YJN,YJO,YJT,YKA,YKC,YKD,YKE,YKF,YKG,YKI,YKJ,YKK,YKL,YKM,YKN,YKQ,YKS,YKT,YKU,YKX,YKY,YLB,YLC,YLD,YLE,YLF,YLG,YLH,YLI,YLJ,YLL,YLM,YLN,YLP,YLQ,YLR,YLS,YLT,YLW,YLX,YLY,YMA,YMB,
	YMC,YMD,YME,YMF,YMG,YMH,YMI,YMJ,YML,YMM,YMN,YMO,YMP,YMQ,YMR,YMS,YMT,YMW,YNA,YNB,YNC,YND,YNE,YNG,YNH,YNI,YNJ,YNK,YNL,YNM,YNO,YNR,YNS,YNT,YNZ,YOC,YOD,YOE,YOG,YOH,YOJ,YOK,YOL,YOO,YOP,YOS,YOW,YOY,YPA,YPB,YPC,YPD,YPE,YPF,YPG,YPH,YPI,YPJ,YPL,YPM,YPN,YPO,YPP,YPQ,YPR,YPT,YPW,YPX,YPY,YPZ,YQA,YQB,YQC,YQD,YQE,YQF,YQG,YQH,YQI,YQK,YQL,YQM,YQN,YQQ,YQR,YQS,YQT,YQU,YQV,YQW,YQX,YQY,YQZ,YRA,YRB,YRD,YRE,YRF,YRG,YRI,
	YRJ,YRL,YRM,YRN,YRQ,YRR,YRS,YRT,YRV,YSA,YSB,YSC,YSD,YSE,YSF,YSG,YSH,YSI,YSJ,YSK,YSL,YSM,YSN,YSO,YSP,YSQ,YSR,YSS,YST,YSU,YSV,YSX,YSY,YSZ,YTA,YTB,YTC,YTD,YTE,YTF,YTG,YTH,YTI,YTJ,YTK,YTL,YTN,YTO,YTQ,YTR,YTS,YTT,YTU,YTX,YTY,YUA,YUB,YUD,YUE,YUF,YUJ,YUM,YUN,YUT,YUX,YUY,YVA,YVB,YVC,YVD,YVE,YVG,YVM,YVN,YVO,YVP,YVQ,YVR,YVT,YVV,YVZ,YWA,YWB,YWG,YWJ,YWK,YWL,YWM,YWN,YWP,YWR,YWS,YWY,YXC,YXE,YXF,YXH,YXI,YXJ,YXK,
	YXL,YXN,YXP,YXQ,YXR,YXS,YXT,YXU,YXX,YXY,YXZ,YYA,YYB,YYC,YYD,YYE,YYF,YYG,YYH,YYI,YYJ,YYL,YYM,YYN,YYQ,YYR,YYT,YYU,YYW,YYY,YZA,YZC,YZE,YZF,YZG,YZH,YZL,YZM,YZP,YZR,YZS,YZT,YZU,YZV,YZW,YZX,ZAA,ZAC,ZAD,ZAG,ZAH,ZAJ,ZAL,ZAM,ZAO,ZAR,ZAT,ZAU,ZAW,ZAX,ZAZ,ZBD,ZBF,ZBK,ZBL,ZBM,ZBO,ZBR,ZBY,ZBZ,ZCC,ZCL,ZCO,ZDF,ZDH,ZEC,ZEG,ZEL,ZEM,ZEN,ZER,ZFA,ZFB,ZFD,ZFL,ZFM,ZFN,ZFR,ZFW,ZGF,ZGI,ZGL,ZGM,ZGR,ZGS,ZGU,ZHA,ZHM,ZHP,ZHZ,
	ZIC,ZIG,ZIH,ZIN,ZIV,ZJG,ZKB,ZKE,ZKG,ZKL,ZKM,ZKP,ZLG,ZLO,ZLT,ZLU,ZMD,ZMG,ZMH,ZMM,ZMT,ZNC,ZND,ZNE,ZNG,ZNU,ZNV,ZNZ,ZOF,ZOS,ZPB,ZPC,ZPH,ZPO,ZPY,ZQN,ZQS,ZRH,ZRI,ZRJ,ZRM,ZRO,ZRR,ZRS,ZRW,ZSA,ZSE,ZSJ,ZSP,ZSS,ZST,ZSZ,ZTA,ZTB,ZTH,ZTJ,ZTM,ZTR,ZTS,ZTZ,ZUC,ZUD,ZUE,ZUH,ZUL,ZUM,ZVA,ZVG,ZVK,ZWA,ZWL,ZYI,ZYL,ZZC,ZZU,ZZV ;
	
	public static Set<String> toStringSet () {
		final Set<String>  set = new HashSet<String>();
		for (CityCodeSZ cc : values ()){
			set.add(cc.toString());
		}
		return set ;
	}
}