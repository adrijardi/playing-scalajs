package spatutorial.client.modules

import diode.data.Pot
import diode.react.ModelProxy
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB}
import spatutorial.client.components.Bootstrap.Panel
import spatutorial.shared.Tell
import japgolly.scalajs.react.vdom.prefix_<^._
import spatutorial.client.components.GlobalStyles

import scalacss.ScalaCssReact._

/**
  * Created by adrij_000 on 28/06/2016.
  */
object Tell {

  @inline private def bss = GlobalStyles.bootstrapStyles

  case class Props(proxy: ModelProxy[Pot[Tell]])

  case class State(selectedItem: Option[Tell] = None)

  class Backend(t: BackendScope[Props, State]) {
    def sayIt(): Callback = {
      println("it")
    }

    def render(p: Props, s: State) =
      Panel(Panel.Props("What do you want to say?"),
        <.div(bss.formGroup,
          <.label(^.`for` := "tellText", "I want to tell you:"),
          <.input(^.id := "tellText", ^.name := "tellText"),
          <.button(^.onClick ==> sayIt
        )
      )
  }

  val component = ReactComponentB[Props]("Tell")
    .initialState(State())
    .renderBackend[Backend]
    .build

  def apply(proxy: ModelProxy[Pot[Tell]]) = component(Props(proxy))
}
