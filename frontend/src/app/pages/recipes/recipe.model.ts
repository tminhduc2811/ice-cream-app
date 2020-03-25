export class Recipe {

  public id: number;

  public userId: number;

  public icecreamId: number;

  public title: string;

  public description: string;

  public price: number;

  public status: number;

  public viewCount: number;

  public image: string;

  public details: string;

  public uploadDate: Date;

  constructor(id: number, userId: number, icecreamId: number, title: string, description: string,
              price: number, status: number, viewCount: number,
              image: string, details: string, uploadDate: Date) {

    this.id = id;
    this.userId = userId;
    this.icecreamId = icecreamId;
    this.title = title;
    this.description = description;
    this.price = price;
    this.status = status;
    this.viewCount = viewCount;
    this.image = image;
    this.details = details;
    this.uploadDate = uploadDate;
  }
}
