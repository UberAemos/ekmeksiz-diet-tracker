import React, { Component } from "react";
import "./App.css"
import Ekmeksiz from './component/Ekmeksiz'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faCaretLeft, faCaretRight } from '@fortawesome/free-solid-svg-icons'

library.add(faCaretLeft, faCaretRight)

class App extends Component {
  render() {
    return (
      <div className="App text-center">
        <Ekmeksiz />
      </div>
    )
  }
}

export default App;