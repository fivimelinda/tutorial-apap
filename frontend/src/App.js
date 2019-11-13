import React from 'react';
import List from "./components/List";
import dummyItems from "./items.json";

export default class App extends React.Component{
  state = {
    favItems:[],
    show: false,
  };

  handleItemClick = item => {
    const newItems = [...this.state.favItems];
    const newItem = { ...item};

    const targetInd = newItems.findIndex(it => it.id === newItem.id);
    if (targetInd < 0) newItems.push(newItem);

    this.setState({favItems: newItems});
  };
  handleItemClick_2 = item => {
    const newItems = [...this.state.favItems];
    const newItem = { ...item};

    const targetInd = newItems.findIndex(it => it.id === newItem.id);
    newItems.splice(targetInd, 1);

    this.setState({favItems: newItems});
  };
  showFavorite = item => {
    this.setState((prevState) => ({show: !prevState.show}));

  };
  render(){
    const { favItems } = this.state;
    return (
      <div className="container-fluid">
        <h1 className="text-center">
          Welcome!
          <small>Class-based</small>
        </h1>
        <div className="container pt-3">
          <div className="row">
            <div className="col-sm">
              <List
                title="Our Menu"
                items={dummyItems}
                onItemClick={this.handleItemClick}
              />
            </div>
            <div className="col-sm">
            <input type="checkbox" onClick={this.showFavorite}/>Show Favorite
            {
              this.state.show &&
              <List
              title="My Favorite"
              items={favItems}
              onItemClick={this.handleItemClick_2}
              />
            } 
            </div>
          </div>
        </div>
      </div>
    );
  }
}

