/**
 *
 ~ DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 ~
 ~ Copyright (c) 2011-2013 ForgeRock AS. All Rights Reserved
 ~
 ~ The contents of this file are subject to the terms
 ~ of the Common Development and Distribution License
 ~ (the License). You may not use this file except in
 ~ compliance with the License.
 ~
 ~ You can obtain a copy of the License at
 ~ http://forgerock.org/license/CDDLv1.0.html
 ~ See the License for the specific language governing
 ~ permission and limitations under the License.
 ~
 ~ When distributing Covered Code, include this CDDL
 ~ Header Notice in each file and include the License file
 ~ at http://forgerock.org/license/CDDLv1.0.html
 ~ If applicable, add the following below the CDDL Header,
 ~ with the fields enclosed by brackets [] replaced by
 ~ your own identifying information:
 ~ "Portions Copyrighted [year] [name of copyright owner]"
 *
 */
package org.forgerock.openam.xacml.v3.Functions;

/*
urn:oasis:names:tc:xacml:1.0:function:string-equal
This function SHALL take two arguments of data-type “http://www.w3.org/2001/XMLSchema#string”
and SHALL return an “http://www.w3.org/2001/XMLSchema#boolean”.
The function SHALL return "True" if and only if the value of both of its arguments
are of equal length and each string is determined to be equal.
Otherwise, it SHALL return “False”.
The comparison SHALL use Unicode codepoint collation,
as defined for the identifier http://www.w3.org/2005/xpath-functions/collation/codepoint by [XF].
*/

import org.forgerock.openam.xacml.v3.Entitlements.*;

import java.util.List;

public class AnyOf extends XACMLFunction {

    public AnyOf()  {
    }
    public FunctionArgument evaluate( XACMLEvalContext pip) throws XACML3EntitlementException {

        FunctionArgument retVal = FunctionArgument.falseObject;

        int args = getArgCount();
        if (args < 3) {
            throw new NotApplicableException("Not enough arguments");
        }
        XACMLFunction func = (XACMLFunction)getArg(0);
        FunctionArgument bag = getArg(args-1).evaluate(pip);
        if (bag instanceof DataValue) {
            bag = new DataBag(bag.getType().getTypeName(),(DataValue)bag);
        }
        if (!(bag instanceof DataBag))  {
            throw new NotApplicableException("AnyOf applied to NON bag");
        }

        for (int i = 1; i < args -1; i++) {
            FunctionArgument res = getArg(i).evaluate(pip);
            List<DataValue> bagVals = (List<DataValue>)bag.getValue(pip);
            boolean oneIsTrue = false;

            for (DataValue dv :  bagVals)  {
                func.clearArguments();
                func.addArgument(res).addArgument(dv);
                FunctionArgument result = func.evaluate(pip);
                if (result.isTrue()) {
                    oneIsTrue = true;
                }
            }
            if (oneIsTrue) {
                retVal = FunctionArgument.trueObject;
            }
        }
        return retVal;
    }
    public String toXML(String type) {
        String retVal = "";
        /*
             Handle Match AnyOf and AllOf specially
        */

        if (type.equals("Match")) {
            retVal = "<AnyOf>" ;
        } else if (type.equals("Allow")) {
            retVal = "<Allow FunctionId=\"" + functionID + "\">" ;
        }

        for (FunctionArgument arg : arguments){
            retVal = retVal + arg.toXML(type);
        }
        if (type.equals("Match")) {
            retVal = retVal + "</AnyOf>" ;
        } else if (type.equals("Allow")) {
            retVal = retVal + "</Allow>" ;
        }

        return retVal;
    }

}
