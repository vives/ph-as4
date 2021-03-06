package com.helger.as4server.receive.soap;

import java.util.List;

import javax.annotation.Nonnull;

import org.w3c.dom.Element;

import com.helger.as4server.receive.AS4MessageState;
import com.helger.commons.errorlist.IErrorBase;
import com.helger.commons.state.ESuccess;

/**
 * Base interface for SOAP header processors that are invoked for incoming
 * messages.
 *
 * @author Philip Helger
 */
public interface ISOAPHeaderElementProcessor
{
  /**
   * Process the passed header element.
   *
   * @param aElement
   *        The DOM node with the header element. Never <code>null</code>.
   * @param aState
   *        The current processing state. Never <code>null</code>.
   * @param aErrorList
   *        The error list to be filled in case there are processing errors.
   *        Never <code>null</code>.
   * @return Never <code>null</code>. If {@link ESuccess#FAILURE} than the
   *         header is treated as "not handled".
   */
  @Nonnull
  ESuccess processHeaderElement (@Nonnull Element aElement,
                                 @Nonnull AS4MessageState aState,
                                 @Nonnull List <? super IErrorBase <?>> aErrorList);
}
