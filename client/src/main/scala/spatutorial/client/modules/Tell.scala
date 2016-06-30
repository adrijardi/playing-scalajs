package spatutorial.client.modules

import diode.data.Pot
import diode.react.ModelProxy
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB}
import spatutorial.client.components.Bootstrap.Panel
import spatutorial.shared.TellItem
import japgolly.scalajs.react.vdom.prefix_<^._
import spatutorial.client.components.GlobalStyles

import scalacss.ScalaCssReact._

/**
  * Created by adrij_000 on 28/06/2016.
  */
object Tell {

  @inline private def bss = GlobalStyles.bootstrapStyles

  case class Props(proxy: ModelProxy[Pot[TellItem]])

  case class State(selectedItem: Option[TellItem] = None)

  class Backend(t: BackendScope[Props, State]) {
    def sayIt(): Callback = {
      println("it")
      t.modState(s => s.copy(selectedItem = Some(TellItem("It!!"))))
      t.
    }

    def render(p: Props, s: State) =
      Panel(Panel.Props("What do you want to say?"),
        <.div(bss.formGroup,
          <.label(^.`for` := "tellText", "I want to tell you:"),
          <.input(^.id := "tellText", ^.name := "tellText"),
          <.button(^.onClick --> sayIt, "Say it!")
        )
      )
  }

  val component = ReactComponentB[Props]("Tell")
    .initialState(State())
    .renderBackend[Backend]
    .build

  def apply(proxy: ModelProxy[Pot[TellItem]]) = component(Props(proxy))
}
