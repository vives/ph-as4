package com.helger.as4lib.model.pmode;

import com.helger.commons.collection.ext.CommonsLinkedHashMap;
import com.helger.commons.collection.ext.ICommonsOrderedMap;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.convert.MicroTypeConverter;

public class PModeLegBusinessInformationMicroTypeConverter extends AbstractPModeMicroTypeConverter
{
  private static final String ATTR_SERVICE = "Service";
  private static final String ATTR_ACTION = "Action";
  private static final String ELEMENT_PROPERTIES = "Properties";
  private static final String ELEMENT_PAYLOAD_PROFILE = "PayloadProfile";
  private static final String ATTR_PAYLOAD_PROFILE_MAX_KB = "PayloadProfileMaxKB";
  private static final String ATTR_MPCID = "MPCID";

  public IMicroElement convertToMicroElement (final Object aObject, final String sNamespaceURI, final String sTagName)
  {
    final PModeLegBusinessInformation aValue = (PModeLegBusinessInformation) aObject;
    final IMicroElement ret = new MicroElement (sNamespaceURI, sTagName);
    ret.setAttribute (ATTR_SERVICE, aValue.getService ());
    ret.setAttribute (ATTR_ACTION, aValue.getAction ());
    aValue.forAllProperties (x -> ret.appendChild (MicroTypeConverter.convertToMicroElement (x,
                                                                                             sNamespaceURI,
                                                                                             ELEMENT_PROPERTIES)));
    aValue.forAllPayloadProfiles (x -> ret.appendChild (MicroTypeConverter.convertToMicroElement (x,
                                                                                                  sNamespaceURI,
                                                                                                  ELEMENT_PAYLOAD_PROFILE)));
    ret.setAttribute (ATTR_PAYLOAD_PROFILE_MAX_KB, aValue.getPayloadProfileMaxKB ());
    ret.setAttribute (ATTR_MPCID, aValue.getMPCID ());
    return ret;
  }

  public PModeLegBusinessInformation convertToNative (final IMicroElement aElement)
  {
    final String sService = aElement.getAttributeValue (ATTR_SERVICE);
    final String sAction = aElement.getAttributeValue (ATTR_ACTION);

    final ICommonsOrderedMap <String, PModeProperty> aProperties = new CommonsLinkedHashMap<> ();
    for (final IMicroElement aPropertyElement : aElement.getAllChildElements (ELEMENT_PROPERTIES))
    {
      final PModeProperty aProperty = MicroTypeConverter.convertToNative (aPropertyElement, PModeProperty.class);
      aProperties.put (aProperty.getName (), aProperty);
    }

    final ICommonsOrderedMap <String, PModePayloadProfile> aPayloadProfiles = new CommonsLinkedHashMap<> ();
    for (final IMicroElement aPayloadElement : aElement.getAllChildElements (ELEMENT_PAYLOAD_PROFILE))
    {
      final PModePayloadProfile aPayloadProfile = MicroTypeConverter.convertToNative (aPayloadElement,
                                                                                      PModePayloadProfile.class);
      aPayloadProfiles.put (aPayloadProfile.getName (), aPayloadProfile);
    }

    final Integer aPayloadProfileMaxKB = aElement.getAttributeValueWithConversion (ATTR_PAYLOAD_PROFILE_MAX_KB,
                                                                                   Integer.class);
    final String sMPCID = aElement.getAttributeValue (ATTR_MPCID);

    return new PModeLegBusinessInformation (sService,
                                            sAction,
                                            aProperties,
                                            aPayloadProfiles,
                                            aPayloadProfileMaxKB,
                                            sMPCID);
  }

}
