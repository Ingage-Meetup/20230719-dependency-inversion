import { open, Database } from 'sqlite'
import sqlite3 from 'sqlite3'

export class User {
    public id: number | null
    public firstName: string
    public lastName: string
    public birthday: Date

    constructor(firstName: string, lastName: string, birthday: Date, id: number | null = null) {
        this.id = id
        this.lastName = lastName
        this.firstName = firstName
        this.birthday = birthday
    }

    toString(): string {
        return JSON.stringify(this, null, '  ')
    }
}

export class UserService {
    private userRepository: UserRepository

    constructor(userRepository: UserRepository = new SqliteInMemoryUserRepository()) {
        this.userRepository = userRepository
    }

    async addUser(lastName: string, firstName: string, birthday: Date): Promise<User | undefined> {
        return this.userRepository.create(new User(lastName, firstName, birthday))
    }

    async getUser(id: number): Promise<User | undefined> {
        return this.userRepository.read(id)
    }

    async listAllUsers(): Promise<Array<User> | undefined> {
        return this.userRepository.list()
    }

    async updateUser(user: User): Promise<User | undefined> {
        return this.userRepository.update(user)
    }

    async deleteUser(id: number): Promise<User | undefined> {
        return this.userRepository.delete(id)
    }
}

export interface UserRepository {
    create(user: User): Promise<User | undefined>
    list(): Promise<Array<User> | undefined>
    read(id: number): Promise<User | undefined>
    update(user: User): Promise<User | undefined>
    delete(id: number): Promise<User | undefined>
}

export class SqliteInMemoryUserRepository implements UserRepository {
    private db: Database<sqlite3.Database, sqlite3.Statement> | null = null

    async initDb() {
        this.db = await open({
            filename: ':memory:',
            driver: sqlite3.Database
        })

        await this.db.run(`
            CREATE TABLE user (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                first_name TEXT,
                last_name TEXT,
                birthday TEXT
            )
        `)
    }

    private readonly initialized = this.initDb()

    async create(user: User): Promise<User | undefined> {
        await this.initialized
        await this.db?.run('INSERT INTO user (first_name, last_name, birthday) VALUES (:firstName, :lastName, :birthday)', {
            ':firstName': user.firstName,
            ':lastName': user.lastName,
            ':birthday': user.birthday.toISOString()
        })

        const inserted = await this.db?.get('SELECT last_insert_rowid() as id')
        return this.read(inserted['id']!)
    }

    async read(id: number): Promise<User | undefined> {
        await this.initialized
        return this.db?.get<User>("SELECT id, first_name AS firstName, last_name AS lastName, strftime('%Y-%m-%d', birthday) AS birthday FROM user WHERE id = :id", {
            ':id': id
        })
    }

    async list(): Promise<Array<User> | undefined> {
        await this.initialized
        return this.db?.all<Array<User>>("SELECT id, firstName AS firstName, last_name AS lastName, strftime('%Y-%m-%d', birthday) AS birthday FROM user")
    }

    async update(user: User): Promise<User | undefined> {
        await this.initialized
        await this.db?.run('UPDATE user SET first_name = :firstName, last_name = :lastName, birthday = :birthday WHERE id = :id', {
            ':firstName': user.firstName,
            ':lastName': user.lastName,
            ':birthday': user.birthday.toISOString(),
            ':id': user.id!!
        })

        return this.read(user.id!!)
    }

    async delete(id: number): Promise<User | undefined> {
        await this.initialized
        const user = await this.read(id)
        await this.db?.run('DELETE FROM user WHERE id = :id', {
            ':id': id
        })
        return user
    }
}
