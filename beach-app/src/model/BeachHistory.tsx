export class BeachHistory {
    public get getId(): number {
        return this.id;
    }
    public set setId(value: number) {
        this.id = value;
    }
    public get getBeach_name(): string {
        return this.beach_name;
    }
    public set setBeach_name(value: string) {
        this.beach_name = value;
    }
    public get getRating(): number {
        return this.rating;
    }
    public set setRating(value: number) {
        this.rating = value;
    }
    public get getRating_date(): Date {
        return this.rating_date;
    }
    public set setRating_date(value: Date) {
        this.rating_date = value;
    }
    public get getUser(): string {
        return this.user;
    }
    public set setUser(value: string) {
        this.user = value;
    }
  
    private user: string;
    private rating_date: Date;
    private rating: number;
    private id: number;
    private beach_name: string;

    // constructor(id: number, beach_name: string, rating: number, user: string, rating_date: Date) {
    //     this.id = id;
    //     this.beach_name = beach_name;
    //     this.rating = rating;
    //     this.user = user;
    //     this.rating_date = rating_date;

    //   }
  }