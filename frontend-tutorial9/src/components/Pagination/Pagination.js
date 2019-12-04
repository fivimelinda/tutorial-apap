import React from "react";
import classes from "./Pagination.module.css";
const Pagination = ({count, item, paginate}) => {
    const pages = [];
    for (let i=1; i<= Math.ceil(count / item); i++){
        pages.push(i);
    }

    return (
            <div >
                {pages.map(number => (
                    <p className={classes.Page-item}>
                        <a className={classes.PageNumberLayout} onClick={() => paginate(number)} href="!#">{number}</a>
                    </p>
                ))}
            </div>
    )
}

export default Pagination;