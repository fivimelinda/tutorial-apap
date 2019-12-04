import React, { Component } from 'react';
import classes from "./Restorans.module.css";
import Restoran from '../../components/Restoran/Restoran';
import Pagination from '../../components/Pagination/Pagination';
import axios from '../../axios-restoran';
import Modal from '../../components/UI/Modal/Modal';
import Button from '../../components/UI/Button/Button';
class Restorans extends Component{
    constructor(props){
        super(props);
        this.state = {
            restorans:[],
            isCreate: false,
            isEdit: false,
            isLoading: true,
            nama:"",
            alamat:"",
            nomorTelepon:"",
            rating:"",
            currentPage:1,
            item:5
        }
    }

    componentDidMount(){
        this.loadRestorans();
    }

    loadRestorans = async () => {
        const fetchedRestorans = [];
        const response = await axios.get("/restorans");
        for (let key in response.data){
            fetchedRestorans.push({
                ...response.data[key]
            });
        }
        this.setState({
            restorans: fetchedRestorans
        });
    };

    addRestoranHandler = () =>{
        this.setState({isCreate:true});
    }
    canceledHandler = () => {
        this.setState({isCreate:false, isEdit:false});
    }
    //Handler untuk perubahan setiap value pada form
    changeHandler = event =>{
        const{name, value}=event.target;
        this.setState({[name]:value});
    }

    submitAddRestoranHandler = event => {
        event.preventDefault();
        this.addRestoran();
        this.canceledHandler();
        this.setState({
            isLoading:true,
            nama:"",
            alamat:"",
            rating:"",
            nomorTelepon:""
        });
    }

    async addRestoran() {
        const restoranToAdd = {
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };
        await axios.post("/restoran", restoranToAdd);
        await this.loadRestorans();
    }

    editRestoranHandler(restoran){
        this.setState({
            isEdit: true,
            idRestoran: restoran.idRestoran,
            nama: restoran.nama,
            nomorTelepon: restoran.nomorTelepon,
            rating: restoran.rating,
            alamat: restoran.alamat
        });
    }

    submitEditRestoranHandler = event => {
        console.log("editing")
        event.preventDefault();
        this.editRestoran();
        this.canceledHandler();
        this.setState({
            isLoading:true,
            nama:"",
            alamat:"",
            rating:"",
            nomorTelepon:""
        });
    }

    async editRestoran(){
        const restoranToEdit = {
            idRestoran: this.state.idRestoran,
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };
        await axios.put("/restoran/" + this.state.idRestoran, restoranToEdit);
        await this.loadRestorans();
        this.canceledHandler();
    }
    
    async deleteRestoranHandler(restoranId){
        await axios.delete(`/restoran/${restoranId}`);
        await this.loadRestorans();
    }

    filterList = event => {
        const word= event.target.value;
        this.filter(word);
    }

    async filter(word){
        const listRestoran = [];
        const getRestoran = await axios.get("/restorans");
        for (let index in getRestoran.data){
            if (getRestoran.data[index].nama.toLowerCase().startsWith(word.toLowerCase())){
                listRestoran.push({...getRestoran.data[index]});
            }
        }
        this.setState({
            restorans: listRestoran
        });
    }

    renderForm(){
        const{isEdit} = this.state;
        return(
            <form>
                <input 
                    className={classes.Input}
                    name="nama"
                    type="text"
                    placeholder="Nama"
                    value={this.state.nama}
                    onChange={this.changeHandler}
                />
                <input
                    className={classes.Input}
                    name="nomorTelepon"
                    type="number"
                    placeholder="Nomor Telepon"
                    value={this.state.nomorTelepon}
                    onChange={this.changeHandler}
                />
                <textarea
                    className={classes.TextArea}
                    name="alamat"
                    type="text"
                    placeholder="Alamat"
                    value={this.state.alamat}
                    onChange={this.changeHandler}
                />
                <input
                    className={classes.Input}
                    name="rating"
                    type="number"
                    placeholder="Rating"
                    value={this.state.rating}
                    onChange={this.changeHandler}
                />
                <Button btnType="Danger" onClick={this.canceledHandler}>
                    CANCEL
                </Button>
                <Button btnType="Success" onClick={
                    isEdit ? this.submitEditRestoranHandler : this.submitAddRestoranHandler}>
                    SUBMIT
                </Button>
            </form>
        );
    }

    // shouldComponentUpdate(nextProps, nextState){
    //     console.log("shouldComponentUpdate");
    //     return true;
    // }

    // loadingHandler = () => {
    //     const currentIsLoading = this.state.isLoading;
    //     this.setState({isLoading:!(currentIsLoading)});
    //     console.log(this.state.isLoading);
    // }

    render(){
        const endPage = this.state.currentPage*this.state.item;
        const startPage = endPage - this.state.item;
        const restoranInPage = this.state.restorans.slice(startPage, endPage);
        const paginate = (pageNumber) => this.setState({currentPage:pageNumber});
        return(
        <React.Fragment>
            <Modal show={this.state.isCreate || this.state.isEdit}
                modalClosed={this.canceledHandler}>
                {this.renderForm()}
            </Modal>
            <div className={classes.Title}>All Restorans</div>
            <div className={classes.ButtonLayout}>
                <button 
                    className={classes.AddRestoranButton}
                    onClick={this.addRestoranHandler}
                >
                    + Add New Restoran
                </button>
            </div>
            <div className={classes.Search}>
                <input type="text" className="form-control" placeholder="Search" onChange={this.filterList}/>
            </div>
            <div className={classes.Restorans}>
                {this.state.restorans &&
                restoranInPage.map(restoran =>
                        <Restoran
                            key={restoran.id}
                            nama={restoran.nama}
                            alamat={restoran.alamat}
                            nomorTelepon={restoran.nomorTelepon}
                            edit={() => this.editRestoranHandler(restoran)}
                            delete={() => this.deleteRestoranHandler(restoran.idRestoran)}
                        />
                    )}
            </div>
            <div className={classes.PaginationLayout}>
                    <Pagination count={this.state.restorans.length} item={this.state.item} paginate={paginate}/>
            </div>
        </React.Fragment>
        );
    }
}
export default Restorans;
