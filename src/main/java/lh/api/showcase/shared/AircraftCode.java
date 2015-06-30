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

public enum AircraftCode {
	
	_100, _141, _142, _143, _146, _14F, _14X, _14Y, _14Z, _310, _312, _313, _318, _319, 
	_31F, _31H, _31W, _31X, _31Y, _320, _321, _32A, _32H, _32L, _32S, _330, _332, _333, _33P, 
	_33Q, _340, _342, _343, _345, _346, _34D, _34E, _34L, _34P, _34R, _34V, _34Y, _34Z, _351, 
	_358, _359, _380, _388, _38F, _38Q, _38Z, _703, _707, _70F, _70M, _717, _721, _722, _727, 
	_72B, _72C, _72F, _72M, _72S, _72X, _72Y, _731, _732, _733, _734, _735, _736, _737, _738, 
	_739, _73C, _73E, _73F, _73G, _73H, _73J, _73M, _73N, _73W, _73X, _73Y, _741, _742, _743, 
	_744, _747, _74C, _74D, _74E, _74F, _74H, _74J, _74L, _74M, _74N, _74P, _74Q, _74R, _74T, 
	_74U, _74V, _74X, _74Y, _74Z, _752, _753, _757, _75F, _75M, _75T, _75W, _762, _763, _764, 
	_767, _76F, _76W, _76X, _76Y, _772, _773, _777, _77L, _77W, _783, _787, _788, _789, _A26, 
	_A28, _A30, _A32, _A40, _A4F, _AB3, _AB4, _AB6, _ABB, _ABF, _ABX, _ABY, _ACD, _ACP, _ACT, 
	_AGH, _ALM, _AN4, _AN6, _AN7, _ANF, _APH, _AR1, _AR7, _AR8, _ARJ, _AT4, _AT5, _AT7, _ATP, 
	_ATR, _B11, _B12, _B13, _B14, _B15, _B72, _BE1, _BE2, _BEC, _BEH, _BEP, _BES, _BET, _BH2, 
	_BNI, _BNT, _CCJ, _CCX, _CD2, _CL4, _CN1, _CN2, _CNA, _CNC, _CNJ, _CNT, _CR1, _CR2, _CR7, 
	_CR9, _CRA, _CRF, _CRJ, _CRK, _CRN, _CRV, _CS1, _CS2, _CS5, _CV4, _CV5, _CVF, _CVR, _CVV, 
	_CVX, _CVY, _CWC, _D10, _D11, _D1C, _D1F, _D1M, _D1X, _D1Y, _D28, _D38, _D3F, _D6F, _D8F, 
	_D8L, _D8M, _D8Q, _D8T, _D8X, _D8Y, _D91, _D92, _D93, _D94, _D95, _D9C, _D9F, _D9X, _DC3, 
	_DC6, _DC8, _DC9, _DF2, _DF3, _DFL, _DH1, _DH2, _DH3, _DH4, _DH7, _DH8, _DHB, _DHC, _DHD, 
	_DHH, _DHL, _DHN, _DHO, _DHP, _DHR, _DHS, _DHT, _E70, _E75, _E90, _E95, _E9F, _E9N, _EM2, 
	_EMB, _EMJ, _ER3, _ER4, _ERD, _ERJ, _F21, _F22, _F23, _F24, _F27, _F28, _F50, _F70, _FK7, 
	_FRJ, _GRG, _GRJ, _GRM, _GRS, _H25, _HEC, _HS7, _I14, _I93, _I9F, _I9M, _I9X, _I9Y, _IL6, 
	_IL7, _IL8, _IL9, _ILW, _J31, _J32, _J41, _JST, _JU5, _L10, _L11, _L15, _L1F, _L49, _L4T, 
	_LOE, _LOF, _LOH, _LOM, _LRJ, _M11, _M1F, _M1M, _M80, _M81, _M82, _M83, _M87, _M88, _M90, 
	_MBH, _MD9, _MIH, _MU2, _ND2, _NDC, _NDE, _NDH, _PA1, _PA2, _PAG, _PAT, _PL2, _PL6, _PN6, 
	_S20, _S58, _S61, _S76, _SF3, _SFB, _SH3, _SH6, _SHB, _SHS, _SSC, _SWM, _T20, _TU3, _TU5, 
	_VCV, _WWP, _YK2, _YK4, _YN2, _YN7, _YS1 ;

	@Override
	public String toString() {
		String str = super.toString();
		return str.replaceFirst("_", "");
	}

	public static AircraftCode enumValueOf(String str) {
		if (str == null) {
			throw new NullPointerException();
		}
		for (AircraftCode ac : values()) {
			if (ac.toString().equalsIgnoreCase(str)) {
				return ac;
			}
		}
		throw new IllegalArgumentException();
	}
	
	public static Set<String> toStringSet () {
		final Set<String>  set = new HashSet<String>();
		for (AircraftCode ac : values ()){
			set.add(ac.toString());
		}
		return set ;
	}
}
