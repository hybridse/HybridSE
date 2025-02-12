package gov.nasa.jpf.symbc;

import gov.nasa.jpf.vm.Types;
import main.corana.external.connector.ArithmeticUtils;
import main.corana.pojos.BitVec;

public class LIA2BVConverter {
	protected static BitVec fromJPFArgument(Object arg, int... type) {
		BitVec res = null;
		int t_type = (type.length > 0) ? type[0]: -1;
		if (arg instanceof Integer || t_type == Types.T_INT) {
			res = ArithmeticUtils.IntegerToBitVec((Integer) arg);
		} else if (arg instanceof Double || t_type == Types.T_DOUBLE) {
			res = ArithmeticUtils.DoubleToBitVec((Double) arg);
		} 
		// add more
		return res;
	}

}
