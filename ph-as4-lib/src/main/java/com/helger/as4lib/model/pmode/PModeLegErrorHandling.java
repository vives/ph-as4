/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.as4lib.model.pmode;

import com.helger.commons.state.ETriState;

/**
 * Error handling - This P-Mode group concerns errors generated by the reception
 * of the message (for either a User message or a Signal message, unless
 * indicated otherwise) sent over leg 1 of the MEP.
 *
 * @author Philip Helger
 */
public class PModeLegErrorHandling
{
  /**
   * This parameter indicates the address, or comma-separated list of addresses,
   * to which to send ebMS errors generated by the MSH that was trying to send
   * the message in error.
   */
  private PModeAddressList m_aReportSenderErrorsTo;

  /**
   * This parameter indicates the address, or comma-separated list of addresses,
   * to which to send ebMS errors generated by the MSH that receives the message
   * in error; e.g. this may be the address of the MSH sending the message in
   * error.
   */
  private PModeAddressList m_aReportReceiverErrorsTo;

  /**
   * This Boolean parameter indicates whether (if "true") errors generated from
   * receiving a message in error are sent over the back-channel of the
   * underlying protocol associated with the message in error, or not.
   */
  private ETriState m_eReportAsResponse;

  /**
   * This Boolean parameter indicates whether (if "true") the Consumer
   * (application/party) of a User Message matching this P-Mode should be
   * notified when an error occurs in the Receiving MSH, during processing of
   * the received User message.
   */
  private ETriState m_eReportProcessErrorNotifyConsumer;
  /**
   * This Boolean parameter indicates whether (if "true") the Producer
   * (application/party) of a User Message matching this P-Mode should be
   * notified when an error occurs in the Sending MSH, during processing of the
   * User Message to be sent.
   */
  private ETriState m_eReportProcessErrorNotifyProducer;

  /**
   * This Boolean parameter indicates whether (if "true") the Producer
   * (application/party) of a User Message matching this P-Mode must always be
   * notified when the delivery to Consumer failed, or whether (if "false"), in
   * some cases, it is sufficient to notify the Consumer only
   * (Report.ProcessErrorNotifyConsumer="true"). This assumes that
   * Reliability.AtLeastOnce.Contract is "true". This also assumes that the
   * Sending MSH implementation has the ability to determine or to be made aware
   * of all cases of non-delivery that occur after the message has been received
   * by the Receiving MSH.
   */
  private ETriState m_eReportDeliveryFailuresNotifyProducer;
}