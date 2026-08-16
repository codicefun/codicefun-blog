# Codicefun Blog

A corporate editorial blog platform built with Next.js full-stack. It is designed for organizations, teams, and products that publish multi-author content with an editorial review workflow — think JetBrains Blog style: one site, multiple authors, editors control what goes live.

> 简体中文说明见 [README.zh-CN.md](README.zh-CN.md)

## Features

**Current**

- Next.js 16 with App Router, React 19, and TypeScript
- Tailwind CSS 4
- pnpm as the package manager

**Planned**

- Editorial workflow: draft → submit → review → publish (with scheduled publishing)
- Roles: administrator, editor, author, reader
- Product-line categories, tags, author pages, archives
- Markdown editing with code highlighting
- SEO: metadata, sitemap, RSS
- International-ready: English-first UI with i18n
- Newsletter subscription

## Tech Stack

| Layer | Choice |
| --- | --- |
| Framework | Next.js 16 (App Router) |
| UI | React 19 + Tailwind CSS 4 |
| Language | TypeScript |
| Package manager | pnpm |

## Getting Started

### Prerequisites

- Node.js 20+ (LTS recommended)
- pnpm

### Install and run

```bash
pnpm install
pnpm dev
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

### Scripts

| Command | Description |
| --- | --- |
| `pnpm dev` | Start the development server |
| `pnpm build` | Create a production build |
| `pnpm start` | Run the production build |
| `pnpm lint` | Run ESLint |

## Project Structure

```
src/
└─ app/
   ├─ layout.tsx   # Root layout shared by all pages
   ├─ page.tsx     # Home page
   └─ globals.css  # Global styles and Tailwind configuration
```

## Roadmap

1. Authentication and roles (Better Auth)
2. Article writing and the editorial workflow
3. Public pages: article list, detail, categories, tags, authors
4. Admin dashboard and content management
5. i18n (English-first, Chinese secondary)
6. Newsletter, search, tests, and deployment

## License

[MIT](LICENSE)
