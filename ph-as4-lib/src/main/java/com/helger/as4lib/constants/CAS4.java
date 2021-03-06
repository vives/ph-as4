package com.helger.as4lib.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class CAS4
{
  // XSD
  public static final String PATH_SCHEMATA = "/schemas/";
  public static final String XSD_EBMS_HEADER = PATH_SCHEMATA + "ebms-header-3_0-200704.xsd";
  public static final String XSD_SOAP11 = PATH_SCHEMATA + "soap11.xsd";
  public static final String XSD_SOAP12 = PATH_SCHEMATA + "soap12.xsd";
  public static final String XSD_XML = PATH_SCHEMATA + "xml.xsd";

  // Namespaces
  public static final String EBMS_NS = "http://docs.oasis-open.org/ebxml-msg/ebms/v3.0/ns/core/200704/";
  public static final String WSSE_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
  public static final String WSU_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
  public static final String DS_NS = "http://www.w3.org/2000/09/xmldsig#";

  // Algorithm
  public static final String DIGEST_ALGORITHM_SHA256 = "http://www.w3.org/2001/04/xmlenc#sha256";
  public static final String SIGNATURE_ALGORITHM_RSA_SHA256 = "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256";

  private CAS4 ()
  {}
}
