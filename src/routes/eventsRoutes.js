const express = require("express");
const router = express.Router();

const mongoose = require('mongoose');

mongoose.connect('mongodb://127.0.0.1:27017/events');

mongoose.connection.on('connected', () => {
  console.log('MongoDB conectado');
});

mongoose.connection.on('error', (err) => {
  console.error('Erro na conexão com o MongoDB:', err);
});

const blogSchema = new mongoose.Schema({
  title: String,
  description: String,
  comments: [{ body: String, date: Date }],
  location: String,
  date: { type: Date, default: Date.now },
  //time: { type: Time, default: Time.now }
});

const Blog = mongoose.model('Blog', blogSchema);

//const { v4: uuidv4 } = require("uuid");
//const fs = require("fs");
//const { time } = require("console");

//var eventsDB = loadEvents();

// Função que carrega os eventos a partir do arquivo JSON
// function loadEvents() {
//   try {
//     return JSON.parse(fs.readFileSync("./src/db/events.json", "utf8"));
//   } catch (err) {
//     console.log(err);
//     return [];
//   }
// }
// // Função para salvar os eventos no arquivo JSON
// function saveEvents() {
//   try {
//     fs.writeFileSync("./src/db/events.json", JSON.stringify(eventsDB, null, 2));
//     return "Saved";
//   } catch (err) {
//     return "Not saved";
//   }
// }

// /**
//  * @swagger
//  * components:
//  *   schemas:
//  *     Event:
//  *       type: object
//  *       required:
//  *         - title
//  *         - date
//  *         - location
//  *       properties:
//  *         id:
//  *           type: string
//  *           description: O id é gerado automáticamente pelo cadastro do evento
//  *         title:
//  *           type: string
//  *           description: Nome do Evento
//  *         date:
//  *           type: date
//  *           description: Data do Evento
//  *         location:
//  *           type: string
//  *           description: Local do Evento
//  *       example:
//  *         id: "c6b3a3a2p4l2r6x1"
//  *         title: Palestra - Transformando o Ensino Especial para Todos
//  *         date: "2024-09-10"
//  *         location: Auditório Ruy Hulse - UNESC, Criciúma - SC
//  */

// /**
//  * @swagger
//  * tags:
//  *   name: Events
//  *   description:
//  *     API de Controle de Eventos
//  *     **Por Luís Augusto Paulo**
//  */

// /**
//  * @swagger
//  * /events:
//  *   get:
//  *     summary: Retorna uma lista de todos os eventos
//  *     tags: [Events]
//  *     responses:
//  *       200:
//  *         description: A lista de eventos
//  *         content:
//  *           application/json:
//  *             schema:
//  *               type: array
//  *               items:
//  *                 $ref: '#/components/schemas/Event'
//  */

//Retornar todos os eventos
// GET "/events"
router.get("/", async (req, res) => {
  try {
    const docs = await Blog.find();
    res.status(200).json(docs);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
  //console.log("getroute");
  //studentsDB = loadEvents();
  //res.json(eventsDB);
});

// /**
//  * @swagger
//  * /events/{id}:
//  *   get:
//  *     summary: Retorna um evento pelo ID
//  *     tags: [Events]
//  *     parameters:
//  *       - in: path
//  *         name: id
//  *         schema:
//  *           type: string
//  *         required: true
//  *         description: ID do evento
//  *     responses:
//  *       200:
//  *         description: Um evento pelo ID
//  *         contens:
//  *           application/json:
//  *             schema:
//  *               $ref: '#/components/schemas/Event'
//  *       404:
//  *         description: Evento não encontrado
//  */

//Retornar um evento específico
// GET "/events/:event"
router.get("/:event", async (req, res) => {
  const nome = req.params.event;
  try {
    const docs = await Blog.find({ event: título });
    res.json(docs);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
  // eventsDB = loadEvents();
  // var event = eventsDB.find((event) => event.id === id);
  // if (!event)
  //   return res.status(404).json({
  //     erro: "Evento não encontrado!",
  //   });
  // res.json(event);
});

// /**
//  * @swagger
//  * /events/date/{date}:
//  *   get:
//  *     summary: Retorna um evento pela data
//  *     tags: [Events]
//  *     parameters:
//  *       - in: path
//  *         name: date
//  *         schema:
//  *           type: string
//  *           format: date
//  *         required: true
//  *         description: Data do evento no formato ANO-MES-DIA
//  *     responses:
//  *       200:
//  *         description: Um evento pela data
//  *         contens:
//  *           application/json:
//  *             schema:
//  *               $ref: '#/components/schemas/Event'
//  *       404:
//  *         description: Data do evento não encontrada
//  */

// //GET /events/date/2024-09-19
// router.get("/date/:date", (req, res) => {
//   const date = req.params.date;
//   eventsDB = loadEvents();

//   var event = eventsDB.filter((event) => event.date === date);
//   if (event.lenght === 0) {
//     return res.status(404).json({
//       erro: "Data não encontrada!",
//     });
//   }
//   res.json(event);
// });

// /**
//  * @swagger
//  * /events:
//  *   post:
//  *     summary: Cria um novo evento
//  *     tags: [Events]
//  *     requestBody:
//  *       required: true
//  *       content:
//  *         application/json:
//  *           schema:
//  *             $ref: '#/components/schemas/Event'
//  *     responses:
//  *       200:
//  *         description: O evento foi criado com sucesso
//  *         content:
//  *           application/json:
//  *             schema:
//  *               $ref: '#/components/schemas/Event'
//  */

//Inserir um novo evento
// POST "/events" BODY { "title": "Atividades Recreativas - Diversão e Movimento: Esportes Adaptados", "comments": "Organização: Comitê Paralímpico Catarinense", "description": "Evento que promove competições e atividades recreativas adaptadas, como esportes paralímpicos e jogos inclusivos. Focado em promover a atividade física e a diversão para todos", "location": "Ginásio Municipal de Criciúma, Criciúma - SC", "date": "2024-09-19", "time":  "13:30:00"}
router.post("/", async (req, res) => {
  const event = req.body;
  try {
    const newPost = await Blog.create(event);
    console.log('Objeto salvo com sucesso!');
    res.json(newPost);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
  // const newEvent = {
  //   id: uuidv4(),
  //   ...req.body,
  // };
  // console.log(newEvent);
  // eventsDB = loadEvents();
  // eventsDB.push(newEvent);
  // let result = saveEvents();
  // console.log(result);
  // return res.json(newEvent);
});

// /**
//  * @swagger
//  * /events/{id}:
//  *  put:
//  *    summary: Atualiza um evento pelo ID
//  *    tags: [Events]
//  *    parameters:
//  *      - in: path
//  *        name: id
//  *        schema:
//  *          type: string
//  *        required: true
//  *        description: ID do evento
//  *    requestBody:
//  *      required: true
//  *      content:
//  *        application/json:
//  *          schema:
//  *            $ref: '#/components/schemas/Event'
//  *    responses:
//  *      200:
//  *        description: O evento foi atualizado com sucesso
//  *        content:
//  *          application/json:
//  *            schema:
//  *              $ref: '#/components/schemas/Event'
//  *      404:
//  *        description: Evento não encontrado
//  */

//Alterar um evento
// PUT "/events" BODY { "title": "Atividades Recreativas - Diversão e Movimento: Esportes Adaptados", "comments": "Organização: Comitê Paralímpico Catarinense", "description": "Evento que promove competições e atividades recreativas adaptadas, como esportes paralímpicos e jogos inclusivos. Focado em promover a atividade física e a diversão para todos", "location": "Ginásio Municipal de Criciúma, Criciúma - SC", "date": "2024-09-19", "time":  "13:30:00"}
router.put("/:id", async (req, res) => {
  const id = req.params.id;
  const n_event = req.body;
  try {
    const updatedPost = await Blog.findByIdAndUpdate(id, { title: n_event.title, description: n_event.description }, { new: true });
    console.log('Objeto Atualizado:', updatedPost);
    res.json(updatedPost);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
  // eventsDB = loadEvents();
  // const currentEvent = eventsDB.find((event) => event.id === id);
  // const currentIndex = eventsDB.findIndex((event) => event.id === id);
  // if (!currentEvent)
  //   return res.status(404).json({
  //     erro: "Evento não encontrado!",
  //   });
  // eventsDB[currentIndex] = newEvent;
  // let result = saveEvents();
  // console.log(result);
  // return res.json(newEvent);
});

// /**
//  * @swagger
//  * /events/{id}:
//  *   delete:
//  *     summary: Remove um evento pelo ID
//  *     tags: [Events]
//  *     parameters:
//  *       - in: path
//  *         name: id
//  *         schema:
//  *           type: string
//  *         required: true
//  *         description: ID do evento
//  *     responses:
//  *       200:
//  *         description: O evento foi removido com sucesso
//  *         content:
//  *          application/json:
//  *            schema:
//  *              $ref: '#/components/schemas/Event'
//  *       404:
//  *         description: Evento não encontrado
//  */

//Deletar um evento
// DELETE "/events/:id"
router.delete("/:id", async (req, res) => {
  const id = req.params.id;
  try {
    const deletedPost = await Blog.findByIdAndDelete(id);
    console.log('Objeto deletado:', deletedPost);
    res.json(deletedPost);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
  // eventsDB = loadEvents();
  // const currentEvent = eventsDB.find((event) => event.id === id);
  // const currentIndex = eventsDB.findIndex((event) => event.id === id);
  // if (!currentEvent)
  //   return res.status(404).json({
  //     erro: "Evento não encontrado!",
  //   });
  // var deletado = eventsDB.splice(currentIndex, 1);
  // let result = saveEvents();
  // console.log(result);
  // res.json(deletado);
});

module.exports = router;
