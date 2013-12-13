// 
// Copyright (c) Microsoft and contributors.  All rights reserved.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//   http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// 
// See the License for the specific language governing permissions and
// limitations under the License.
// 

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.management.servicebus.models;

/**
* Statistics about the current usage of a service bus entity.
*/
public class CountDetails
{
    private int activeMessageCount;
    
    /**
    * The current number of active messages.
    */
    public int getActiveMessageCount() { return this.activeMessageCount; }
    
    /**
    * The current number of active messages.
    */
    public void setActiveMessageCount(int activeMessageCount) { this.activeMessageCount = activeMessageCount; }
    
    private int deadLetterMessageCount;
    
    /**
    * The current number of dead letters.
    */
    public int getDeadLetterMessageCount() { return this.deadLetterMessageCount; }
    
    /**
    * The current number of dead letters.
    */
    public void setDeadLetterMessageCount(int deadLetterMessageCount) { this.deadLetterMessageCount = deadLetterMessageCount; }
    
    private int scheduledMessageCount;
    
    /**
    * The current number of scheduled messages.
    */
    public int getScheduledMessageCount() { return this.scheduledMessageCount; }
    
    /**
    * The current number of scheduled messages.
    */
    public void setScheduledMessageCount(int scheduledMessageCount) { this.scheduledMessageCount = scheduledMessageCount; }
    
    private int transferDeadLetterMessageCount;
    
    /**
    * The current number of transfer dead letters.
    */
    public int getTransferDeadLetterMessageCount() { return this.transferDeadLetterMessageCount; }
    
    /**
    * The current number of transfer dead letters.
    */
    public void setTransferDeadLetterMessageCount(int transferDeadLetterMessageCount) { this.transferDeadLetterMessageCount = transferDeadLetterMessageCount; }
    
    private int transferMessageCount;
    
    /**
    * The current number of transfer messages.
    */
    public int getTransferMessageCount() { return this.transferMessageCount; }
    
    /**
    * The current number of transfer messages.
    */
    public void setTransferMessageCount(int transferMessageCount) { this.transferMessageCount = transferMessageCount; }
    
    /**
    * Initializes a new instance of the CountDetails class.
    *
    */
    public CountDetails()
    {
    }
}
