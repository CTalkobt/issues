/**
 * Root problem is that java.lang.illegalStateException: Queue full was 
 * occured during seda route processing.
 * <p>
 * Scenario for routes: 
 * <p>
 *    I've got a scenario where I need to iterate through all users within a 
 *    system and send them to a remote as part of a routine "sync" to ensure 
 *    that both systems are in agreement. 
 * <p>
 *     User imports are also supported so there is some initial aggregation done
 *     on id in an attempt to minimize updates to the 3rd party system.   
 *     It's ok to send duplicates over, just not ideal so the initial aggregation
 *     has a size / timeout based aggregation. 
 * <p>
 *     The 3rd party system (2nd step) is intended to aggregate and send those updates in 
 *     fixed size batches.   Changing it to a simple "mock:blah" doesn't change 
 *     behavior at this point.
 *<p>
 * Solution: 
 * <p>
 *   On the producer side (producerTemplate.send..  the following parameters need
 *   to be used:  blockWhenFull=true&offerTimeout=50000 (or some large value). 
 *   The offerTimeout must be > 0 for the blockWhenFull to be enabled.  Existing
 *   Camel docs document the offerTimeout but don't seem to clarify it's 
 *   requirement when blockWhenFull=true. 
 *<p>
 * See boolean useSolution in {@link TestStartup}
 */
package net.ctalkobt.issue.camel.seda.queuefull;
