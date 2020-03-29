package section_5.section_5_3

import section_5.section_5_2.Add
import section_5.section_5_3.{Backend, Frontend}


object LoadBalancingCluster extends App {

    Frontend.initiate()
    Backend.initiate(2551)
    Backend.initiate(2552)
    Backend.initiate(2561)
    Thread.sleep(10000)
    Frontend.initiate()
}
