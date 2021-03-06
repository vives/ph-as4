package com.helger.as4server.servlet;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;

import com.helger.commons.ValueEnforcer;

/**
 * This class represents a single DOM element in a SOAP header with some
 * metadata.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class AS4SOAPHeader
{
  private final Element m_aNode;
  private final QName m_aQName;
  private final boolean m_bIsMustUnderstand;
  private boolean m_bProcessed = false;

  /**
   * @param aNode
   *        The DOM element. May not be <code>null</code>.
   * @param aQName
   *        The QName of the DOM element. May not be <code>null</code>. Must
   *        match the QName of the provided DOM node.
   * @param bIsMustUnderstand
   *        <code>true</code> if this is a must understand header,
   *        <code>false</code> otherwise.
   */
  public AS4SOAPHeader (@Nonnull final Element aNode, @Nonnull final QName aQName, final boolean bIsMustUnderstand)
  {
    m_aNode = ValueEnforcer.notNull (aNode, "Node");
    m_aQName = ValueEnforcer.notNull (aQName, "QName");
    m_bIsMustUnderstand = bIsMustUnderstand;
  }

  @Nonnull
  public Element getNode ()
  {
    return m_aNode;
  }

  @Nonnull
  public QName getQName ()
  {
    return m_aQName;
  }

  public boolean isMustUnderstand ()
  {
    return m_bIsMustUnderstand;
  }

  public boolean isProcessed ()
  {
    return m_bProcessed;
  }

  public void setProcessed (final boolean bProcessed)
  {
    m_bProcessed = bProcessed;
  }
}
