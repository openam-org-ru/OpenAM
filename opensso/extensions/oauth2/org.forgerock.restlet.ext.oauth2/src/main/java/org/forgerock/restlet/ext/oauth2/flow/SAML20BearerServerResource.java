/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright © 2012 ForgeRock AS. All rights reserved.
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at
 * http://forgerock.org/license/CDDLv1.0.html
 * See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at http://forgerock.org/license/CDDLv1.0.html
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * $Id$
 */
package org.forgerock.restlet.ext.oauth2.flow;

import org.forgerock.restlet.ext.oauth2.OAuth2;

/**
 * @author $author$
 * @version $Revision$ $Date$
 */
public class SAML20BearerServerResource extends AbstractFlow {

    /*
2.1.  Using SAML20BearerServerResource Assertions as Authorization Grants

   To use a SAML20BearerServerResource Bearer Assertion as an authorization grant, use the
   following parameter values and encodings.

   The value of "grant_type" parameter MUST be
   "urn:ietf:params:oauth:grant-type:saml2-bearer"

   The value of the "assertion" parameter MUST contain a single SAML20BearerServerResource 2.0
   Assertion.  The SAML20BearerServerResource Assertion XML data MUST be encoded using
   base64url, where the encoding adheres to the definition in Section 5
   of RFC4648 [RFC4648] and where the padding bits are set to zero.  To
   avoid the need for subsequent encoding steps (by "application/
   x-www-form-urlencoded" [W3C.REC-html401-19991224], for example), the
   base64url encoded data SHOULD NOT be line wrapped and pad characters
   ("=") SHOULD NOT be included.
     */


    /*
    2.2.  Using SAML20BearerServerResource Assertions for Client Authentication

   To use a SAML20BearerServerResource Bearer Assertion for client authentication grant, use
   the following parameter values and encodings.


   The value of "client_assertion_type" parameter MUST be
   "urn:ietf:params:oauth:client-assertion-type:saml2-bearer"

   The value of the "client_assertion" parameter MUST contain a single
   SAML20BearerServerResource 2.0 Assertion.  The SAML20BearerServerResource Assertion XML data MUST be encoded
   using base64url, where the encoding adheres to the definition in
   Section 5 of RFC4648 [RFC4648] and where the padding bits are set to
   zero.  To avoid the need for subsequent encoding steps (by
   "application/x-www-form-urlencoded" [W3C.REC-html401-19991224], for
   example), the base64url encoded data SHOULD NOT be line wrapped and
   pad characters ("=") SHOULD NOT be included.
     */

    @Override
    protected String[] getRequiredParameters() {
        return new String[]{OAuth2.Params.GRANT_TYPE};
    }
}
