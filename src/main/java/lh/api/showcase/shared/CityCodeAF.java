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

public enum CityCodeAF {
	
	AAA,AAB,AAC,AAE,AAF,AAH,AAI,AAJ,AAK,AAL,AAM,AAN,AAO,AAQ,AAR,AAS,AAT,AAU,AAV,AAX,AAY,ABA,ABD,ABE,ABF,ABG,ABH,ABI,ABJ,ABK,ABL,ABM,ABN,ABO,ABP,ABQ,ABR,ABS,ABT,ABU,ABV,ABW,ABX,ABY,ABZ,ACA,ACB,ACC,ACD,ACE,ACH,ACI,ACK,ACL,ACM,ACN,ACO,ACR,ACS,ACT,ACU,ACV,ADA,ADD,ADE,ADF,ADG,ADH,ADI,ADK,ADL,ADM,ADN,ADO,ADP,ADQ,ADR,ADT,ADU,ADV,ADW,ADY,ADZ,AEA,AEG,AEH,AEK,AEL,AEO,AER,AES,AET,AEY,AFA,AFD,AFI,AFL,AFN,AFO,
	AFR,AFT,AFY,AGA,AGD,AGE,AGF,AGG,AGH,AGI,AGJ,AGK,AGL,AGM,AGN,AGO,AGP,AGQ,AGR,AGS,AGT,AGU,AGV,AGW,AGX,AGY,AGZ,AHB,AHC,AHH,AHI,AHL,AHN,AHO,AHS,AHT,AHU,AHY,AHZ,AIA,AIB,AIC,AID,AIE,AIF,AIG,AII,AIK,AIL,AIM,AIN,AIO,AIP,AIR,AIS,AIT,AIU,AIV,AIY,AIZ,AJA,AJF,AJI,AJJ,AJL,AJN,AJO,AJR,AJS,AJU,AJY,AKA,AKB,AKD,AKE,AKF,AKG,AKI,AKJ,AKK,AKL,AKM,AKN,AKO,AKP,AKQ,AKR,AKS,AKT,AKU,AKV,AKX,AKY,ALA,ALB,ALC,ALD,ALE,ALF,ALG,
	ALH,ALI,ALJ,ALK,ALL,ALM,ALN,ALO,ALP,ALQ,ALR,ALS,ALT,ALU,ALW,ALX,ALY,ALZ,AMA,AMB,AMC,AMD,AME,AMF,AMG,AMH,AMI,AMJ,AML,AMM,AMN,AMO,AMP,AMQ,AMR,AMS,AMT,AMU,AMV,AMW,AMX,AMY,AMZ,ANA,ANB,ANC,AND,ANE,ANF,ANG,ANH,ANI,ANJ,ANK,ANL,ANM,ANN,ANO,ANP,ANQ,ANR,ANS,ANT,ANU,ANV,ANW,ANX,ANY,ANZ,AOA,AOB,AOC,AOD,AOG,AOH,AOI,AOK,AOL,AON,AOO,AOR,AOS,AOU,APB,APC,APE,APF,APH,API,APK,APL,APN,APO,APP,APQ,APR,APS,APT,APU,APV,
	APW,APX,APY,APZ,AQA,AQG,AQI,AQJ,AQM,AQP,AQS,AQY,ARB,ARC,ARD,ARE,ARF,ARG,ARH,ARI,ARJ,ARK,ARL,ARM,ARO,ARP,ARQ,ARR,ARS,ART,ARU,ARV,ARW,ARX,ARY,ARZ,ASA,ASB,ASC,ASD,ASE,ASF,ASG,ASH,ASI,ASJ,ASK,ASL,ASM,ASN,ASO,ASP,ASQ,ASR,AST,ASU,ASV,ASW,ASX,ASY,ASZ,ATA,ATB,ATC,ATD,ATE,ATF,ATG,ATH,ATI,ATJ,ATK,ATL,ATM,ATN,ATO,ATP,ATQ,ATR,ATS,ATT,ATU,ATV,ATW,ATX,ATY,ATZ,AUA,AUB,AUC,AUD,AUE,AUG,AUH,AUI,AUJ,AUK,AUL,AUM,AUN,
	AUO,AUP,AUQ,AUR,AUS,AUT,AUU,AUW,AUX,AUY,AUZ,AVB,AVF,AVG,AVI,AVK,AVL,AVN,AVO,AVP,AVU,AVV,AVX,AWA,AWB,AWD,AWE,AWH,AWK,AWM,AWN,AWP,AWR,AWZ,AXA,AXB,AXC,AXD,AXG,AXK,AXL,AXM,AXN,AXP,AXR,AXT,AXU,AXV,AXX,AYA,AYC,AYD,AYE,AYG,AYH,AYI,AYK,AYL,AYN,AYP,AYQ,AYR,AYS,AYT,AYU,AYW,AYZ,AZB,AZD,AZG,AZN,AZO,AZR,AZS,AZT,AZZ,BAA,BAC,BAE,BAF,BAG,BAH,BAI,BAJ,BAK,BAL,BAM,BAN,BAO,BAP,BAQ,BAR,BAS,BAT,BAU,BAV,BAW,BAX,BAY,BAZ,
	BBA,BBB,BBC,BBD,BBE,BBF,BBG,BBH,BBI,BBJ,BBK,BBL,BBM,BBN,BBO,BBP,BBQ,BBR,BBS,BBT,BBV,BBW,BBX,BBY,BBZ,BCA,BCB,BCC,BCD,BCE,BCF,BCG,BCH,BCI,BCJ,BCK,BCL,BCM,BCN,BCO,BCP,BCQ,BCR,BCS,BCT,BCU,BCX,BCY,BCZ,BDA,BDB,BDC,BDD,BDE,BDF,BDG,BDH,BDI,BDJ,BDK,BDM,BDN,BDO,BDP,BDQ,BDR,BDS,BDT,BDU,BDV,BDW,BDX,BDY,BDZ,BEA,BEB,BED,BEE,BEF,BEG,BEH,BEI,BEJ,BEK,BEL,BEM,BEN,BEP,BER,BES,BET,BEU,BEV,BEW,BEX,BEY,BEZ,BFB,BFC,BFD,
	BFE,BFF,BFG,BFJ,BFL,BFN,BFO,BFP,BFR,BFS,BFT,BFU,BFX,BGA,BGB,BGC,BGD,BGE,BGF,BGG,BGH,BGI,BGJ,BGK,BGL,BGM,BGN,BGO,BGP,BGQ,BGR,BGT,BGU,BGV,BGW,BGX,BGZ,BHA,BHB,BHE,BHF,BHG,BHH,BHI,BHJ,BHK,BHL,BHM,BHN,BHO,BHP,BHQ,BHR,BHS,BHT,BHU,BHV,BHX,BHY,BHZ,BIA,BIB,BIC,BID,BIE,BIG,BIH,BII,BIJ,BIK,BIL,BIM,BIN,BIO,BIP,BIQ,BIR,BIS,BIT,BIU,BIV,BIW,BIX,BIY,BIZ,BJA,BJC,BJD,BJF,BJG,BJH,BJI,BJJ,BJK,BJL,BJM,BJN,BJO,BJR,BJS,
	BJU,BJW,BJX,BJZ,BKB,BKC,BKD,BKE,BKF,BKH,BKI,BKJ,BKK,BKM,BKN,BKO,BKP,BKQ,BKR,BKS,BKT,BKU,BKW,BKX,BKY,BKZ,BLA,BLB,BLC,BLD,BLE,BLF,BLG,BLH,BLI,BLJ,BLK,BLL,BLM,BLN,BLO,BLP,BLQ,BLR,BLS,BLT,BLU,BLV,BLW,BLX,BLY,BLZ,BMB,BMC,BMD,BME,BMF,BMG,BMH,BMI,BMJ,BMK,BML,BMM,BMN,BMO,BMP,BMQ,BMR,BMS,BMU,BMV,BMW,BMX,BMY,BMZ,BNA,BNB,BNC,BND,BNE,BNF,BNG,BNI,BNJ,BNK,BNL,BNM,BNN,BNO,BNP,BNQ,BNR,BNS,BNT,BNU,BNV,BNW,BNX,BNY,
	BNZ,BOA,BOB,BOC,BOD,BOE,BOG,BOH,BOI,BOJ,BOK,BOL,BOM,BON,BOO,BOP,BOQ,BOR,BOS,BOT,BOU,BOV,BOW,BOX,BOY,BOZ,BPA,BPB,BPC,BPD,BPF,BPG,BPH,BPI,BPN,BPS,BPT,BPU,BPY,BQA,BQE,BQH,BQL,BQN,BQO,BQQ,BQS,BQT,BQU,BQW,BRA,BRB,BRC,BRD,BRE,BRF,BRG,BRH,BRI,BRJ,BRK,BRL,BRM,BRN,BRO,BRP,BRQ,BRR,BRS,BRT,BRU,BRV,BRW,BRX,BRY,BRZ,BSA,BSB,BSC,BSD,BSE,BSF,BSG,BSH,BSI,BSJ,BSK,BSL,BSN,BSO,BSP,BSQ,BSR,BSS,BST,BSU,BSW,BSX,BSY,BSZ,
	BTA,BTB,BTC,BTD,BTE,BTF,BTG,BTH,BTI,BTJ,BTK,BTL,BTM,BTN,BTO,BTP,BTQ,BTR,BTS,BTT,BTU,BTV,BTW,BTX,BTY,BUA,BUB,BUC,BUD,BUE,BUF,BUG,BUH,BUI,BUJ,BUK,BUL,BUM,BUN,BUO,BUP,BUQ,BUR,BUS,BUT,BUU,BUV,BUW,BUX,BUY,BUZ,BVA,BVB,BVC,BVD,BVE,BVF,BVG,BVH,BVI,BVK,BVL,BVM,BVO,BVP,BVR,BVS,BVU,BVW,BVX,BVY,BVZ,BWA,BWB,BWC,BWD,BWE,BWF,BWG,BWI,BWJ,BWK,BWL,BWM,BWN,BWO,BWP,BWQ,BWS,BWT,BWU,BWY,BXA,BXB,BXC,BXD,BXE,BXH,BXI,BXK,
	BXL,BXM,BXN,BXS,BXT,BXU,BXV,BXX,BYA,BYB,BYC,BYD,BYG,BYH,BYI,BYK,BYL,BYM,BYN,BYQ,BYR,BYS,BYT,BYU,BYW,BYX,BZA,BZC,BZD,BZE,BZG,BZI,BZK,BZL,BZM,BZN,BZO,BZP,BZR,BZT,BZU,BZV,BZY,BZZ,CAA,CAB,CAC,CAD,CAE,CAF,CAG,CAH,CAI,CAJ,CAK,CAL,CAM,CAN,CAO,CAP,CAQ,CAR,CAS,CAT,CAU,CAV,CAW,CAX,CAY,CAZ,CBA,CBB,CBC,CBD,CBE,CBF,CBG,CBH,CBJ,CBK,CBL,CBN,CBO,CBP,CBQ,CBR,CBS,CBT,CBU,CBV,CBX,CBY,CBZ,CCA,CCB,CCC,CCF,CCG,CCH,CCI,
	CCJ,CCK,CCL,CCM,CCN,CCO,CCP,CCQ,CCR,CCS,CCT,CCU,CCV,CCW,CCX,CCY,CCZ,CDA,CDB,CDC,CDE,CDF,CDH,CDJ,CDK,CDL,CDN,CDO,CDP,CDQ,CDR,CDS,CDU,CDV,CDW,CDY,CEB,CEC,CED,CEE,CEF,CEG,CEI,CEJ,CEK,CEL,CEM,CEN,CEO,CEP,CEQ,CER,CES,CET,CEU,CEV,CEW,CEX,CEY,CEZ,CFA,CFD,CFE,CFF,CFG,CFH,CFI,CFN,CFO,CFP,CFR,CFS,CFT,CFU,CFV,CGA,CGB,CGC,CGD,CGE,CGG,CGI,CGJ,CGM,CGN,CGO,CGP,CGQ,CGR,CGS,CGT,CGU,CGV,CGY,CGZ,CHA,CHB,CHC,CHD,CHE,
	CHF,CHG,CHH,CHI,CHJ,CHK,CHL,CHM,CHN,CHO,CHP,CHQ,CHR,CHS,CHT,CHU,CHV,CHW,CHX,CHY,CHZ,CIC,CID,CIE,CIF,CIG,CIH,CIJ,CIK,CIL,CIM,CIN,CIP,CIQ,CIR,CIS,CIT,CIV,CIW,CIX,CIY,CIZ,CJA,CJB,CJC,CJD,CJH,CJL,CJN,CJS,CJU,CKA,CKB,CKC,CKD,CKE,CKG,CKH,CKI,CKK,CKM,CKN,CKO,CKR,CKS,CKV,CKX,CKY,CKZ,CLA,CLB,CLC,CLD,CLE,CLG,CLH,CLI,CLJ,CLL,CLM,CLN,CLO,CLP,CLQ,CLR,CLS,CLT,CLU,CLV,CLX,CLY,CLZ,CMA,CMB,CMC,CMD,CME,CMF,CMG,CMH,
	CMI,CMJ,CMK,CML,CMM,CMO,CMP,CMQ,CMR,CMS,CMT,CMU,CMV,CMW,CMX,CMY,CMZ,CNA,CNB,CNC,CND,CNE,CNG,CNH,CNI,CNJ,CNK,CNL,CNM,CNN,CNO,CNP,CNQ,CNR,CNS,CNT,CNU,CNV,CNX,CNY,CNZ,COA,COB,COC,COD,COE,COG,COH,COJ,COK,COL,COM,CON,COO,COP,COQ,COR,COS,COT,COU,COV,COW,COX,COY,COZ,CPA,CPB,CPC,CPD,CPE,CPF,CPG,CPH,CPL,CPM,CPN,CPO,CPQ,CPR,CPT,CPU,CPV,CPX,CQF,CQP,CQS,CQT,CRA,CRB,CRC,CRD,CRF,CRH,CRI,CRJ,CRL,CRM,CRN,CRO,CRP,
	CRQ,CRR,CRS,CRT,CRU,CRV,CRW,CRX,CRY,CRZ,CSA,CSB,CSC,CSD,CSE,CSF,CSI,CSJ,CSK,CSL,CSN,CSP,CSQ,CSR,CSS,CST,CSV,CSW,CSX,CSY,CTA,CTB,CTC,CTD,CTE,CTG,CTH,CTI,CTK,CTL,CTM,CTN,CTO,CTP,CTQ,CTR,CTT,CTU,CTW,CTX,CTY,CTZ,CUA,CUC,CUD,CUE,CUF,CUH,CUI,CUJ,CUK,CUL,CUM,CUN,CUO,CUP,CUQ,CUR,CUS,CUT,CUU,CUV,CUW,CUY,CUZ,CVB,CVC,CVE,CVF,CVG,CVH,CVI,CVJ,CVL,CVM,CVN,CVO,CVQ,CVR,CVT,CVU,CWB,CWC,CWG,CWI,CWL,CWP,CWR,CWS,CWT,
	CWW,CXA,CXB,CXC,CXF,CXI,CXJ,CXL,CXN,CXO,CXP,CXQ,CXT,CXY,CYA,CYB,CYC,CYE,CYF,CYG,CYI,CYL,CYM,CYO,CYP,CYR,CYS,CYT,CYU,CYX,CYZ,CZA,CZB,CZC,CZE,CZF,CZH,CZJ,CZK,CZL,CZM,CZN,CZO,CZP,CZS,CZT,CZU,CZW,CZX,CZY,CZZ,DAA,DAB,DAC,DAD,DAE,DAF,DAG,DAH,DAI,DAJ,DAM,DAN,DAP,DAR,DAT,DAU,DAV,DAX,DAY,DAZ,DBA,DBD,DBM,DBN,DBO,DBP,DBQ,DBS,DBT,DBV,DBY,DCI,DCK,DCM,DCR,DCT,DCU,DDC,DDG,DDI,DDM,DDN,DDP,DDU,DEB,DEC,DED,DEH,DEI,
	DEL,DEM,DEN,DEO,DEP,DER,DES,DEZ,DFI,DFP,DFW,DGA,DGB,DGC,DGE,DGG,DGN,DGO,DGP,DGR,DGT,DGU,DGW,DHA,DHD,DHI,DHL,DHM,DHN,DHR,DHT,DIB,DIC,DIE,DIJ,DIK,DIL,DIM,DIN,DIO,DIP,DIQ,DIR,DIS,DIU,DIV,DIY,DJA,DJB,DJE,DJG,DJJ,DJM,DJN,DJO,DJU,DKI,DKK,DKR,DKS,DKV,DLA,DLB,DLC,DLD,DLE,DLG,DLH,DLI,DLK,DLL,DLM,DLN,DLO,DLS,DLV,DLY,DLZ,DMB,DMD,DMM,DMN,DMO,DMR,DMT,DMU,DNB,DNC,DND,DNF,DNH,DNI,DNK,DNM,DNN,DNO,DNP,DNQ,DNR,DNS,
	DNU,DNV,DNX,DNZ,DOA,DOB,DOC,DOD,DOE,DOF,DOG,DOH,DOI,DOK,DOL,DOM,DON,DOO,DOP,DOR,DOS,DOU,DOV,DOX,DPE,DPG,DPK,DPL,DPO,DPS,DPU,DQA,DRA,DRB,DRC,DRE,DRF,DRG,DRH,DRI,DRJ,DRM,DRN,DRO,DRR,DRS,DRT,DRU,DRW,DSC,DSD,DSE,DSG,DSI,DSK,DSL,DSM,DSN,DSV,DTA,DTD,DTE,DTH,DTL,DTM,DTR,DTT,DUA,DUB,DUC,DUD,DUE,DUF,DUG,DUI,DUJ,DUK,DUM,DUN,DUQ,DUR,DUS,DUT,DVA,DVL,DVN,DVO,DVP,DVR,DWA,DWB,DXA,DXB,DXD,DXR,DYA,DYG,DYL,DYM,DYR,
	DYU,DYW,DZA,DZI,DZN,DZO,DZU,EAA,EAB,EAE,EAM,EAN,EAR,EAS,EAT,EAU,EBA,EBB,EBD,EBG,EBJ,EBL,EBM,EBN,EBO,EBS,EBU,EBW,ECA,ECG,ECH,ECN,ECO,ECR,ECS,EDA,EDB,EDD,EDE,EDG,EDI,EDK,EDL,EDM,EDQ,EDR,EED,EEK,EEN,EFB,EFG,EFK,EFL,EFO,EFW,EGA,EGC,EGE,EGL,EGM,EGN,EGO,EGP,EGS,EGV,EGX,EHL,EHM,EHT,EIA,EIE,EIH,EIN,EIS,EIY,EJA,EJH,EKA,EKB,EKD,EKE,EKI,EKN,EKO,EKT,EKX,ELA,ELB,ELC,ELD,ELE,ELF,ELG,ELH,ELI,ELJ,ELK,ELL,ELM,ELN,
	ELO,ELP,ELQ,ELR,ELS,ELT,ELU,ELV,ELW,ELY,ELZ,EMA,EMD,EME,EMG,EMI,EMK,EMM,EMN,EMO,EMP,EMS,EMT,EMX,EMY,ENA,ENB,ENE,ENF,ENH,ENI,ENJ,ENK,ENL,ENN,ENO,ENS,ENT,ENU,ENV,ENW,ENY,EOI,EOK,EOR,EOS,EOZ,EPG,EPH,EPI,EPK,EPL,EPN,EPR,EPT,EQS,ERA,ERB,ERC,ERD,ERE,ERF,ERH,ERI,ERM,ERN,ERO,ERR,ERT,ERU,ERV,ERZ,ESA,ESC,ESD,ESE,ESF,ESG,ESH,ESI,ESK,ESL,ESM,ESN,ESO,ESP,ESR,ESS,EST,ESW,ETB,ETD,ETE,ETH,ETN,ETS,ETZ,EUA,EUC,EUE,
	EUF,EUG,EUM,EUN,EUO,EUX,EVA,EVD,EVE,EVG,EVH,EVM,EVN,EVV,EVW,EVX,EWB,EWE,EWI,EWK,EWN,EWO,EWY,EXI,EXM,EXT,EYL,EYP,EYR,EYS,EYW,EZS,FAA,FAB,FAC,FAE,FAF,FAG,FAH,FAI,FAJ,FAK,FAL,FAM,FAN,FAO,FAR,FAS,FAT,FAV,FAY,FBD,FBE,FBL,FBM,FBR,FBY,FCA,FCB,FCY,FDE,FDF,FDH,FDK,FDR,FDU,FDY,FEA,FEB,FEG,FEK,FEL,FEN,FEP,FER,FET,FEZ,FFA,FFD,FFL,FFM,FFT,FFU,FGD,FGL,FGU,FHU,FHZ,FIC,FID,FIE,FIG,FIH,FIK,FIL,FIN,FIV,FIZ,FJR,FKB,
	FKH,FKI,FKJ,FKL,FKN,FKQ,FKS,FLA,FLB,FLC,FLD,FLF,FLG,FLH,FLI,FLJ,FLL,FLM,FLN,FLO,FLP,FLR,FLS,FLT,FLW,FLY,FMA,FMC,FME,FMG,FMH,FMI,FMM,FMN,FMO,FMS,FMY,FNA,FNC,FNE,FNG,FNH,FNI,FNJ,FNK,FNL,FNR,FNT,FOA,FOB,FOC,FOD,FOG,FOK,FOM,FOO,FOP,FOR,FOS,FOT,FOU,FOX,FOY,FPO,FPR,FPY,FRA,FRB,FRC,FRD,FRE,FRG,FRH,FRI,FRJ,FRK,FRL,FRM,FRO,FRP,FRQ,FRR,FRS,FRT,FRU,FRW,FRY,FRZ,FSC,FSD,FSI,FSK,FSL,FSM,FSN,FSP,FST,FSU,FSZ,FTA,
	FTI,FTK,FTL,FTU,FTX,FUB,FUE,FUG,FUJ,FUK,FUL,FUM,FUN,FUO,FUT,FWA,FWL,FWM,FXM,FXO,FXY,FYM,FYN,FYT,FYU,FYV,FZO,FTE;
	
	public static Set<String> toStringSet () {
		final Set<String>  set = new HashSet<String>();
		for (CityCodeAF cc : values ()){
			set.add(cc.toString());
		}
		return set ;
	}
}
