package com.helger.as4lib.message;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.w3c.dom.Node;

import com.helger.as4lib.constants.CAS4;
import com.helger.as4lib.ebms3header.Ebms3MessageInfo;
import com.helger.as4lib.ebms3header.Ebms3Receipt;
import com.helger.as4lib.ebms3header.Ebms3SignalMessage;
import com.helger.as4lib.ebms3header.Ebms3UserMessage;
import com.helger.as4lib.soap.ESOAPVersion;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.xml.ChildElementIterator;
import com.helger.xml.XMLHelper;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings ("NP_PARAMETER_MUST_BE_NONNULL_BUT_MARKED_AS_NULLABLE")
public class CreateReceiptMessage
{
  @Nonnull
  @ReturnsMutableCopy
  private ICommonsList <Node> _getAllReferences (@Nullable final Node aUserMessage)
  {
    final ICommonsList <Node> aDSRefs = new CommonsArrayList<> ();
    {
      Node aNext = XMLHelper.getFirstChildElementOfName (aUserMessage, "Header");
      aNext = XMLHelper.getFirstChildElementOfName (aNext, CAS4.WSSE_NS, "Security");
      aNext = XMLHelper.getFirstChildElementOfName (aNext, CAS4.DS_NS, "Signature");
      aNext = XMLHelper.getFirstChildElementOfName (aNext, CAS4.DS_NS, "SignedInfo");
      if (aNext != null)
      {
        new ChildElementIterator (aNext).findAll (XMLHelper.filterElementWithNamespaceAndLocalName (CAS4.DS_NS,
                                                                                                    "Reference"),
                                                  aDSRefs::add);
      }
    }
    return aDSRefs;
  }

  @Nonnull
  public AS4ReceiptMessage createReceiptMessage (@Nonnull final ESOAPVersion eSOAPVersion,
                                                 @Nonnull final Ebms3MessageInfo aEbms3MessageInfo,
                                                 @Nullable final Ebms3UserMessage aEbms3UserMessage,
                                                 @Nullable final Node aSignedUserMessage)
  {
    if (aEbms3UserMessage != null)
      aEbms3MessageInfo.setRefToMessageId (aEbms3UserMessage.getMessageInfo ().getMessageId ());

    // Only for signed messages
    final ICommonsList <Node> aDSRefs = _getAllReferences (aSignedUserMessage);

    final Ebms3SignalMessage aSignalMessage = new Ebms3SignalMessage ();

    // Message Info
    aSignalMessage.setMessageInfo (aEbms3MessageInfo);

    final Ebms3Receipt aEbms3Receipt = new Ebms3Receipt ();
    // PullRequest
    if (aDSRefs.isNotEmpty ())
    {
      for (final Node aRef : aDSRefs)
        aEbms3Receipt.addAny (aRef.cloneNode (true));

    }
    else
    {
      // If the original usermessage is not signed, the receipt will contain the
      // original message part with out wss4j security
      aEbms3Receipt.addAny (new CreateUserMessage ().getUserMessageAsAS4UserMessage (eSOAPVersion, aEbms3UserMessage)
                                                    .getAsSOAPDocument ()
                                                    .getDocumentElement ());
    }
    aSignalMessage.setReceipt (aEbms3Receipt);

    return new AS4ReceiptMessage (eSOAPVersion, aSignalMessage);
  }

  // TODO ReftomessageID maybe not needed here since, it comes with the
  // usermessage
  public Ebms3MessageInfo createEbms3MessageInfo (@Nonnull final String sMessageId,
                                                  @Nullable final String sRefToMessageId)
  {
    return MessageHelperMethods.createEbms3MessageInfo (sMessageId, sRefToMessageId);
  }
}
