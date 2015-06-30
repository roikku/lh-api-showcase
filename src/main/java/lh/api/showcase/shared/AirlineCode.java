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

public enum AirlineCode {
	
	_0A, _0B, _0D, _0J, _0X, _1A, 
	_1C, _1F, _1H, _1I, _1L, _1T, _2B, _2D, _2F, _2G, _2I, _2J, _2K, _2L, _2M, 
	_2N, _2P, _2Q, _2T, _2U, _2W, _2X, _2Z, _3B, _3C, _3E, _3F, _3G, _3I, _3J, 
	_3K, _3L, _3N, _3O, _3P, _3Q, _3R, _3S, _3T, _3U, _3V, _3W, _4A, _4C, _4D, 
	_4F, _4G, _4H, _4K, _4L, _4M, _4N, _4O, _4P, _4Q, _4R, _4S, _4T, _4U, _4X, 
	_4Y, _4Z, _5A, _5C, _5D, _5E, _5F, _5G, _5H, _5J, _5K, _5L, _5M, _5N, _5P, 
	_5Q, _5T, _5V, _5W, _5X, _5Y, _5Z, _6A, _6B, _6C, _6D, _6E, _6F, _6G, _6H, 
	_6I, _6J, _6K, _6N, _6P, _6Q, _6R, _6T, _6U, _6V, _6W, _6Y, _6Z, _7B, _7C, 
	_7E, _7F, _7G, _7H, _7I, _7J, _7K, _7L, _7M, _7N, _7O, _7P, _7Q, _7R, _7T, 
	_7Y, _7Z, _8A, _8B, _8C, _8E, _8F, _8H, _8J, _8K, _8L, _8M, _8N, _8P, _8Q, 
	_8R, _8U, _8V, _8W, _8Y, _8Z, _9A, _9C, _9E, _9F, _9H, _9I, _9J, _9K, _9L, 
	_9N, _9P, _9Q, _9R, _9S, _9T, _9U, _9W, _9X, _9Y, _A1, _A2, 
	_A3, _A4, _A5, _A6, _A7, _A8, _A9, _AA, _AB, _AC, _AD, _AE, _AF, _AG, _AH, 
	_AI, _AJ, _AK, _AL, _AM, _AN, _AO, _AP, _AQ, _AR, _AS, _AT, _AU, _AV, _AW, 
	_AX, _AY, _AZ, _B0, _B1, _B2, _B3, _B4, _B5, _B6, _B7, _B8, _B9, _BA, _BB, 
	_BC, _BD, _BE, _BF, _BG, _BI, _BJ, _BK, _BL, _BM, _BN, _BO, _BP, _BQ, _BR, 
	_BS, _BT, _BU, _BV, _BW, _BX, _BY, _BZ, _C0, _C1, _C2, _C3, _C4, _C5, _C6, 
	_C7, _C8, _C9, _CA, _CB, _CC, _CD, _CE, _CF, _CG, _CH, _CI, _CJ, _CK, _CL, 
	_CM, _CN, _CP, _CQ, _CR, _CS, _CT, _CU, _CV, _CW, _CX, _CY, _CZ, _D1, _D3, 
	_D4, _D5, _D6, _D7, _D8, _D9, _DA, _DB, _DC, _DD, _DE, _DF, _DG, _DH, _DI, 
	_DJ, _DK, _DL, _DN, _DO, _DP, _DR, _DS, _DT, _DU, _DV, _DW, _DX, _DY, _DZ, 
	_E0, _E1, _E2, _E3, _E4, _E5, _E6, _E7, _E8, _E9, _EA, _EC, _ED, _EE, _EF, 
	_EG, _EH, _EI, _EJ, _EK, _EL, _EM, _EN, _EO, _EP, _EQ, _ER, _ES, _ET, _EU, 
	_EV, _EW, _EX, _EY, _EZ, _F1, _F2, _F3, _F4, _F5, _F6, _F7, _F8, _F9, _FA, 
	_FB, _FC, _FD, _FE, _FF, _FG, _FH, _FI, _FJ, _FK, _FL, _FM, _FO, _FP, _FQ, 
	_FR, _FS, _FT, _FU, _FV, _FW, _FX, _FY, _FZ, _G0, _G1, _G2, _G3, _G4, _G5, 
	_G6, _G7, _G8, _G9, _GA, _GB, _GC, _GD, _GE, _GF, _GG, _GH, _GI, _GJ, _GK, 
	_GL, _GM, _GN, _GO, _GP, _GQ, _GR, _GS, _GT, _GU, _GV, _GW, _GY, _GZ, _H1, 
	_H2, _H3, _H4, _H5, _H6, _H8, _H9, _HA, _HB, _HC, _HD, _HE, _HF, _HG, _HH, 
	_HI, _HJ, _HK, _HM, _HN, _HO, _HQ, _HR, _HT, _HU, _HV, _HW, _HX, _HY, _HZ, 
	_I2, _I4, _I5, _I6, _I7, _I8, _I9, _IA, _IB, _IC, _ID, _IE, _IF, _IG, _IH, 
	_II, _IJ, _IK, _IL, _IM, _IN, _IO, _IP, _IQ, _IR, _IT, _IV, _IW, _IX, _IY, 
	_IZ, _J2, _J3, _J4, _J5, _J6, _J7, _J8, _J9, _JA, _JB, _JC, _JD, _JE, _JF, 
	_JH, _JI, _JJ, _JK, _JL, _JM, _JN, _JO, _JP, _JQ, _JR, _JS, _JT, _JU, _JV, 
	_JW, _JX, _JY, _JZ, _K1, _K2, _K4, _K5, _K6, _K7, _K8, _K9, _KA, _KB, _KC, 
	_KD, _KE, _KF, _KG, _KH, _KI, _KJ, _KK, _KL, _KM, _KN, _KO, _KP, _KQ, _KR, 
	_KS, _KT, _KU, _KV, _KW, _KX, _KY, _KZ, _L1, _L2, _L3, _L4, _L5, _L6, _L7, 
	_L8, _L9, _LA, _LB, _LC, _LD, _LF, _LG, _LH, _LI, _LJ, _LK, _LL, _LM, _LN, 
	_LO, _LP, _LQ, _LR, _LS, _LT, _LU, _LV, _LW, _LX, _LY, _M0, _M1, _M2, _M3, 
	_M4, _M5, _M6, _M7, _M8, _M9, _MA, _MB, _MC, _MD, _ME, _MF, _MG, _MH, _MI, 
	_MJ, _MK, _ML, _MM, _MN, _MO, _MP, _MQ, _MR, _MS, _MT, _MU, _MV, _MW, _MX, 
	_MY, _MZ, _N0, _N1, _N2, _N3, _N4, _N5, _N6, _N7, _N8, _N9, _NA, _NB, _NC, 
	_NE, _NF, _NG, _NH, _NI, _NJ, _NK, _NL, _NM, _NN, _NO, _NP, _NQ, _NR, _NS, 
	_NT, _NU, _NV, _NW, _NX, _NY, _NZ, _O1, _O6, _O7, _O8, _OA, _OB, _OC, _OD, 
	_OE, _OG, _OH, _OI, _OJ, _OK, _OL, _OM, _ON, _OO, _OP, _OQ, _OR, _OS, _OT, 
	_OU, _OV, _OW, _OX, _OY, _OZ, _P4, _P5, _P7, _P8, _P9, _PA, _PC, _PD, _PE, 
	_PF, _PG, _PH, _PI, _PJ, _PK, _PL, _PM, _PN, _PO, _PP, _PQ, _PR, _PS, _PT, 
	_PU, _PV, _PW, _PX, _PY, _PZ, _Q2, _Q3, _Q5, _Q6, _Q8, _Q9, _QA, _QB, _QC, 
	_QD, _QE, _QF, _QG, _QH, _QI, _QK, _QL, _QM, _QN, _QO, _QQ, _QR, _QS, _QT, 
	_QU, _QV, _QW, _QX, _QY, _QZ, _R0, _R1, _R2, _R3, _R5, _R7, _R8, _R9, _RA, 
	_RB, _RC, _RD, _RE, _RF, _RG, _RH, _RI, _RJ, _RK, _RL, _RM, _RN, _RO, _RP, 
	_RQ, _RR, _RS, _RU, _RV, _RW, _RX, _RY, _S0, _S1, _S2, _S3, _S4, _S5, _S6, 
	_S7, _S8, _S9, _SA, _SB, _SC, _SD, _SE, _SF, _SG, _SH, _SI, _SJ, _SK, _SL, 
	_SM, _SN, _SO, _SP, _SQ, _SR, _SS, _ST, _SU, _SV, _SW, _SX, _SY, _SZ, _T0, 
	_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8, _T9, _TA, _TB, _TC, _TD, _TE, _TF, 
	_TG, _TH, _TI, _TJ, _TK, _TL, _TM, _TN, _TO, _TP, _TQ, _TR, _TS, _TT, _TU, 
	_TV, _TW, _TX, _TY, _TZ, _U1, _U3, _U4, _U5, _U6, _U7, _U8, _U9, _UA, _UB, 
	_UD, _UE, _UF, _UG, _UI, _UJ, _UK, _UL, _UM, _UN, _UO, _UP, _UQ, _UR, _US, 
	_UT, _UU, _UX, _UY, _UZ, _V0, _V2, _V3, _V4, _V5, _V6, _V7, _V8, _V9, _VA, 
	_VB, _VC, _VD, _VE, _VF, _VG, _VH, _VI, _VJ, _VK, _VL, _VM, _VN, _VO, _VP, 
	_VQ, _VR, _VS, _VT, _VU, _VV, _VW, _VX, _VY, _VZ, _W1, _W2, _W3, _W4, _W5, 
	_W6, _W7, _W8, _W9, _WA, _WB, _WC, _WD, _WE, _WF, _WG, _WH, _WJ, _WK, _WL, 
	_WM, _WN, _WO, _WP, _WQ, _WR, _WS, _WU, _WV, _WW, _WX, _WY, _WZ, _X1, _X3, 
	_X7, _XA, _XB, _XE, _XF, _XG, _XJ, _XK, _XL, _XM, _XN, _XO, _XP, _XQ, _XR, 
	_XS, _XT, _XV, _XW, _XX, _XY, _XZ, _Y0, _Y1, _Y4, _Y5, _Y7, _Y8, _Y9, _YC, 
	_YD, _YE, _YH, _YI, _YL, _YM, _YO, _YP, _YQ, _YR, _YS, _YT, _YV, _YW, _YX, 
	_YY, _YZ, _Z2, _Z3, _Z4, _Z6, _Z7, _Z8, _Z9, _ZA, _ZB, _ZC, _ZE, _ZF, _ZG, 
	_ZH, _ZI, _ZJ, _ZK, _ZL, _ZM, _ZN, _ZP, _ZQ, _ZS, _ZT, _ZU, _ZV, _ZW, _ZX, 
	_ZY, _ZZ, _МИ, _ЯП ;
	
	@Override
	public String toString() {
		String str = super.toString();
		return str.replaceFirst("_", "");
	}

	public static AirlineCode enumValueOf(String str) {
		if (str == null) {
			throw new NullPointerException();
		}
		for (AirlineCode ac : values()) {
			if (ac.toString().equalsIgnoreCase(str)) {
				return ac;
			}
		}
		throw new IllegalArgumentException();
	}
	
	public static Set<String> toStringSet () {
		final Set<String>  set = new HashSet<String>();
		for (AirlineCode ac : values ()){
			set.add(ac.toString());
		}
		return set ;
	}
}
