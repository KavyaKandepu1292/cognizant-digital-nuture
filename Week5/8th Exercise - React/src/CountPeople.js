import React from 'react';

class CountPeople extends React.Component {
  constructor() {
    super();
    this.state = {
      entrycount: 0,
      exitcount: 0
    };
    this.updateEntry = this.updateEntry.bind(this);
    this.updateExit = this.updateExit.bind(this);
  }

  updateEntry() {
    this.setState((prevState, props) => {
      return { entrycount: prevState.entrycount + 1 };
    });
  }

  updateExit() {
    this.setState((prevState, props) => {
      return { exitcount: prevState.exitcount + 1 };
    });
  }

  render() {
    return (
      <div>
        <button onClick={this.updateEntry}>Check In</button>
        <span> {this.state.entrycount} people checked in! </span>
        <button onClick={this.updateExit}>Check Out</button>
        <span> {this.state.exitcount} people checked out! </span>
      </div>
    );
  }
}

export default CountPeople;
