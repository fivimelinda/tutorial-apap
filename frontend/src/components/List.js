import React from "react";

import Item from "./Item";
import EmptyState from "./EmptyState";

export default function List({title, items, onItemClick}){
    let content;
    if (items.length > 0){
        content = items.map(item => (
            <Item key={item.id} item={item} onChange={onItemClick}/>
        ))
    } else {
        content = <EmptyState/>
    }
    return(
        <>
            <h3 style={styles.heading}>{title}</h3>
            <div className="list-group">
                {content}
            </div>
        </>
    );
}

const styles = {
    heading:{
        fontFamily: "courier new"
    }
};