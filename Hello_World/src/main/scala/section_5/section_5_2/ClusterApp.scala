package section_5.section_5_2

object ClusterApp extends App{
  Frontend.initiate()
  Backend.initiate(2552)
  Backend.initiate(2560)
  Backend.initiate(2561)
  Thread.sleep(10000)
  Frontend.getFrontend ! Add(2,4)
}
